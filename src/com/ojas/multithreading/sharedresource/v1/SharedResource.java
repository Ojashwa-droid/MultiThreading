package com.ojas.multithreading.sharedresource.v1;

public class SharedResource {
    boolean itemAvailable = false;

    // Put the item
    public synchronized void addItem() {
        itemAvailable = true;
        System.out.println("Item added by: " + Thread.currentThread().getName() +
                " at " + System.currentTimeMillis() + " and notifying all the waiting threads.");

        notifyAll();
    }

    public synchronized void consumeItem() {
        System.out.println("ConsumeItem by: " + Thread.currentThread().getName() +
                " at " + System.currentTimeMillis());

        // using while loop instead of general if condition, to avoid "spurious wakeup" due to system noise
        while (!itemAvailable) {
            try{
                System.out.println("Thread " + Thread.currentThread().getName() + " is waiting for the item.");
                wait(); // it releases the monitor lock
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Item consumed by: " + Thread.currentThread().getName());
        itemAvailable = false;
    }
}
