package com.server.tasks;

import java.net.Socket;
import java.util.Scanner;

public class DeliveryTask implements Runnable {

    private final Socket socket;

    public DeliveryTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Delivering tasks to " + socket);

            Scanner clientInput = new Scanner(socket.getInputStream());

            while (clientInput.hasNextLine()) {
                String command = clientInput.nextLine();

                System.out.println(command);
            }

            clientInput.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
