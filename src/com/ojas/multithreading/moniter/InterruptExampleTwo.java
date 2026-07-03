package com.ojas.multithreading.moniter;

import java.util.concurrent.atomic.AtomicInteger;

public class InterruptExampleTwo {
    public static void main(String[] args) throws InterruptedException {
        InterruptExampleTwo interruptObj = new InterruptExampleTwo();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                interruptObj.doSomeWork();
            }
        });

        thread.start();

        try {
            Thread.sleep(10000);
            thread.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread.join();

        System.out.println("Main thread is exiting.");
    }

    public void doSomeWork() {
        AtomicInteger counter = new AtomicInteger(0);
        System.out.println(Thread.currentThread().getName() + " is starting.");

        while (!Thread.currentThread().isInterrupted()) {
            counter.incrementAndGet();
            pauseForAWhile();
        }
        System.out.println("Thread was interrupted, now exiting with current count: " + counter.get());
    }

    public static void pauseForAWhile() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // handle some exception
//            throw new RuntimeException("Thread was interrupted, Failed to complete operation");
            Thread.currentThread().interrupt();
        }
    }
}
