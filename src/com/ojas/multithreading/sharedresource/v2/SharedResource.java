package com.ojas.multithreading.sharedresource.v2;

import java.util.logging.Logger;

public class SharedResource {

    Logger logger = Logger.getLogger(SharedResource.class.getName());

    private boolean itemAvailable = false;

    public synchronized void addItem() {
        itemAvailable = true;
        System.out.println("Producer thread " + Thread.currentThread().getName() + " added the item");
        notifyAll();
    }

    public synchronized void consumeItem() {

        System.out.println("Consumer thread " + Thread.currentThread().getName() + " inside consumeThread method");

        while (!itemAvailable) {
            try {
                System.out.println("Consumer thread " + Thread.currentThread().getName() +" waiting for the item");
                wait(); // releases the monitor lock
            } catch (InterruptedException e) {
                // further exception handling goes here
                throw new RuntimeException(e);
            }
        }
        itemAvailable = false;
        System.out.println("Consumer thread " + Thread.currentThread().getName() + " consumed the item");
        notifyAll();
    }
}
