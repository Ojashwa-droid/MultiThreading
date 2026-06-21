package com.ojas.multithreading.sharedresource.v2;

public class ProducerTask implements Runnable {

    private SharedResource sharedResource;

    public ProducerTask(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        sharedResource.addItem();
    }
}
