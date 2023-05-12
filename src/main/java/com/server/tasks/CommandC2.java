package com.server.tasks;

import java.io.PrintStream;

public class CommandC2 implements Runnable {
    private final PrintStream output;

    public CommandC2(PrintStream output) {
        this.output = output;
    }

    @Override
    public void run() {
        System.out.println("Executing command c2");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        output.println("Command c2 executed successfully");
    }
}
