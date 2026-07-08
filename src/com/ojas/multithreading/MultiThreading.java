package com.ojas.multithreading;

import java.util.concurrent.Executors;

public class MultiThreading implements Runnable {
    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        System.out.println("Thread name: " + Thread.currentThread().getName());
        try {
            // ...do some work
            Thread.sleep(1000);
        } catch (InterruptedException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
