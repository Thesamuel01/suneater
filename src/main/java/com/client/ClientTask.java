package com.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTask {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 50000);

        System.out.println("Connection established");

        OutputStream outputStreamClient = socket.getOutputStream();
        PrintStream printStream = new PrintStream(outputStreamClient);
        printStream.println("c1");

        Scanner keyboard = new Scanner(System.in);
        keyboard.nextLine();

        printStream.close();
        keyboard.close();
        socket.close();
    }

}
