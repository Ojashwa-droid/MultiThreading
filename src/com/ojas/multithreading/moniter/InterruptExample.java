package com.ojas.multithreading.moniter;

import java.util.concurrent.atomic.AtomicInteger;

public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);

        Thread thread = new Thread(() -> {
            try {
                System.out.println("Thread "+ Thread.currentThread().getName() +" is starting.");
                while (!Thread.currentThread().isInterrupted()) {
                    counter.incrementAndGet();
                    // ...doing some work
                }
            } catch (Exception e) {
                // TODO: handle exception
                throw new RuntimeException(e);
            }
            System.out.println("Thread was interrupted, now exiting with current count: " + counter.get());

        });
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO: handle exception
            /*
            * Its very important to re-interrupt the thread cause the jvm clears the interrupt flag
            * when the thread is in waiting state.
            */
            Thread.currentThread().interrupt();
        }
        thread.interrupt();
        thread.join();

        System.out.println("Main thread is exiting.");
    }
}
