package com.server.tasks;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class CommandC2AccessDB implements Callable<String> {
    private final PrintStream output;

    public CommandC2AccessDB(PrintStream output) {
        this.output = output;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Executing command c2 - db");

        output.println("processing c2 command - db");

        Thread.sleep(15000);

        int number = new Random().nextInt(100) + 1;

        System.out.println("Command c2 - db executed successfully");
        return Integer.toString(number);
    }
}
