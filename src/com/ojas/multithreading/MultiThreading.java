package com.ojas.multithreading;

public class MultiThreading implements Runnable {
    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        System.out.println("Thread name: " + Thread.currentThread().getName());
    }
}
