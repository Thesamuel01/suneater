package com.server;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerTask {
    public static void main(String[] args) throws Exception {

        System.out.println("Starting server");
        ServerSocket server = new ServerSocket(50000);

        while(true) {
            Socket socket = server.accept();

            System.out.println("Server running on port " + socket.getPort());
        }
    }
}