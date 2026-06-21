package com.ojas.multithreading.sharedresource.v1;

public class ConsumeTask implements Runnable {
    private SharedResource resource;

    public ConsumeTask(SharedResource resource) {
        this.resource = resource;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        System.out.println("Consumer thread: " + Thread.currentThread().getName() + " is running.");
        resource.consumeItem();
    }
}
