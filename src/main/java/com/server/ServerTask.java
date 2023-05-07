package com.server;

import com.server.tasks.DeliveryTask;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTask {
    public static void main(String[] args) throws Exception {

        System.out.println("Starting server");
        ServerSocket server = new ServerSocket(50000);
        ExecutorService threadPool = Executors.newCachedThreadPool();

        while(true) {
            Socket socket = server.accept();
            System.out.println("Server running on port " + socket.getPort());

            DeliveryTask deliveryTask = new DeliveryTask(socket);
            threadPool.execute(deliveryTask);
        }
    }
}