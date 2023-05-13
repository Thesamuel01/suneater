package com.server.tasks;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MergeFutureResults implements Runnable {
    private final Future<String> futureWs;
    private final Future<String> futureDb;
    private final PrintStream clientOutput;

    public MergeFutureResults(Future<String> futureWs, Future<String> futureDb, PrintStream clientOutput) {

        this.futureWs = futureWs;
        this.futureDb = futureDb;
        this.clientOutput = clientOutput;
    }

    @Override
    public void run() {
        System.out.println("Waiting future results");

        try {
            String number1 = this.futureWs.get(15, TimeUnit.SECONDS);
            String number2 = this.futureDb.get(15, TimeUnit.SECONDS);

            this.clientOutput.println("Command c2 result: " + number1 + ", " + number2);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("Timeout: Interrupting c2 command");
            this.clientOutput.println("Timeout on running c2 command");
            this.futureWs.cancel(true);
            this.futureDb.cancel(true);
        }

        System.out.println("Merge task finished");
    }
}
