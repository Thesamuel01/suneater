package com.server.tasks;

import java.util.concurrent.BlockingQueue;

public class Consume implements Runnable {
    private final BlockingQueue<String> commandQueue;

    public Consume(BlockingQueue<String> commandQueue) {
        this.commandQueue = commandQueue;
    }

    @Override
    public void run() {
        try {

            String command = null;

            while ((command = commandQueue.take()) != null) {
                System.out.println("Consuming command " + command);
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
