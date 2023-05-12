package com.server;

import com.server.exceptionHandler.ExceptionHandler;
import com.server.tasks.DeliveryTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerTask {

    private final ServerSocket server;
    private final ExecutorService threadPool;
    private final AtomicBoolean isRunning;
    private static int number = 1;

    public ServerTask() throws IOException {
        System.out.println("Starting server");

        this.server = new ServerSocket(50000);
        this.threadPool = Executors.newFixedThreadPool(4, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "Server Task Thread" + number);

                number++;

                thread.setUncaughtExceptionHandler(new ExceptionHandler());
                return null;
            }
        });
        this.isRunning = new AtomicBoolean(true);
    }

    public void stop() throws IOException {
        isRunning.set(false);
        server.close();
        threadPool.shutdownNow();
    }

    public void run() throws IOException {
        while(this.isRunning.get()) {
            try {
                Socket socket = server.accept();
                System.out.println("Server running on port " + socket.getPort());
                DeliveryTask deliveryTask = new DeliveryTask(threadPool, socket, this);
                threadPool.execute(deliveryTask);
            } catch (SocketException e) {
                System.out.println("SocketException, is it running? " + isRunning);
            }
        }
    }
}