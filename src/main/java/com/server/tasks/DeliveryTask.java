package com.server.tasks;

import com.server.ServerTask;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DeliveryTask implements Runnable {

    private final ExecutorService threadPool;
    private final Socket socket;
    private final ServerTask server;

    public DeliveryTask(ExecutorService threadPool, Socket socket, ServerTask server) {
        this.threadPool = threadPool;
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
                    case "c1": {
                        clientOutput.println("Command confirmed c1");
                        CommandC1 c1 = new CommandC1(clientOutput);
                        threadPool.execute(c1);
                        break;
                    }
                    case "c2": {
                        clientOutput.println("Command confirmed c2");
                        CommandC2CallsWS c2Ws = new CommandC2CallsWS(clientOutput);
                        CommandC2AccessDB c2Db = new CommandC2AccessDB(clientOutput);
                        Future<String> futureWs = threadPool.submit(c2Ws);
                        Future<String> futureDb = threadPool.submit(c2Db);

                        this.threadPool.submit(new MergeFutureResults(futureWs, futureDb, clientOutput));
                        break;
                    }
                    case "shutdown": {
                        clientOutput.println("Shutdown server.");
                        server.stop();

                        break;
                    }
                    default: {
                        clientOutput.println("Command not found.");

                        break;
                    }
                }

                System.out.println(command);
            }

            clientInput.close();
            clientOutput.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
