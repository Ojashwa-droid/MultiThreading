package com.ojas.multithreading.sharedresource.v2;

public class ConsumerTask implements Runnable {
    private SharedResource sharedResource;

    public ConsumerTask(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        sharedResource.consumeItem();
    }
}
