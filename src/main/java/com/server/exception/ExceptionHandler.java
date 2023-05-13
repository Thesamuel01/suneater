package com.server.exception;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Error in thread " + t.getName() + ", " + e.getMessage());
    }
}
