package com.server.tasks;

import com.server.ServerTask;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DeliveryTask implements Runnable {

    private final ExecutorService threadPool;
    private final BlockingQueue<String> queue;
    private final Socket socket;
    private final ServerTask server;

    public DeliveryTask(ExecutorService threadPool, BlockingQueue<String> queue, Socket socket, ServerTask server) {
        this.threadPool = threadPool;
        this.queue = queue;
        this.socket = socket;
        this.server = server;


    }

    @Override
    public void run() {
        try {
            System.out.println("Delivering tasks to " + socket);

            Scanner clientInput = new Scanner(socket.getInputStream());
            PrintStream clientOutput = new PrintStream(socket.getOutputStream());

            while (clientInput.hasNextLine()) {
                String command = clientInput.nextLine();
                System.out.println("Receive command: " + command);

                switch (command) {
                    case "c1" -> {
                        clientOutput.println("Command confirmed c1");
                        CommandC1 c1 = new CommandC1(clientOutput);
                        threadPool.execute(c1);
                    }
                    case "c2" -> {
                        clientOutput.println("Command confirmed c2");
                        CommandC2CallsWS c2Ws = new CommandC2CallsWS(clientOutput);
                        CommandC2AccessDB c2Db = new CommandC2AccessDB(clientOutput);
                        Future<String> futureWs = threadPool.submit(c2Ws);
                        Future<String> futureDb = threadPool.submit(c2Db);

                        this.threadPool.submit(new MergeFutureResults(futureWs, futureDb, clientOutput));
                    }
                    case "c3" -> {
                        this.queue.put(command);
                        clientOutput.println("Command c3 added in queue");
                    }
                    case "shutdown" -> {
                        clientOutput.println("Shutdown server.");
                        server.stop();
                    }
                    default -> {
                        clientOutput.println("Command not found.");

                    }
                }
            }

            clientInput.close();
            clientOutput.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
