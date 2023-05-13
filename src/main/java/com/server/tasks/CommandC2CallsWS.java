package com.server.tasks;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class CommandC2CallsWS implements Callable<String> {
    private final PrintStream output;

    public CommandC2CallsWS(PrintStream output) {
        this.output = output;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Executing command c2 - ws");

        output.println("processing c2 command - ws");

        Thread.sleep(15000);

        int number = new Random().nextInt(100) + 1;

        System.out.println("Command c2 - ws executed successfully");
        return Integer.toString(number);
    }
}
