package com.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTask {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 50000);

        System.out.println("Connection established");

        Thread sendThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OutputStream outputStreamClient = socket.getOutputStream();
                    PrintStream output = new PrintStream(outputStreamClient);
                    Scanner keyboard = new Scanner(System.in);

                    while (keyboard.hasNextLine()) {
                        String line = keyboard.nextLine();

                        if (line.trim().equals("")) {
                            break;
                        }

                        output.println(line);
                    }

                    output.close();
                    keyboard.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread responseThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Receiving server data...");
                    InputStream inputStream = socket.getInputStream();
                    Scanner serverResponse = new Scanner(inputStream);

                    while (serverResponse.hasNextLine()) {
                        String line = serverResponse.nextLine();
                        System.out.println(line);
                    }

                    serverResponse.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        sendThread.start();
        responseThread.start();
        sendThread.join();

        System.out.println("Closing socket " + socket);
        socket.close();
    }
}
