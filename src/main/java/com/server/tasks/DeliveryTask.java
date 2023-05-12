package com.server.tasks;

import com.server.ServerTask;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

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
                        clientOutput.println("Command confirmed c1" );
                        CommandC1 c1 = new CommandC1(clientOutput);
                        threadPool.execute(c1);
                        break;
                    }

                    case "c2": {
                        clientOutput.println("Command confirmed c2");
                        CommandC2 c2 = new CommandC2(clientOutput);
                        threadPool.execute(c2);
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
