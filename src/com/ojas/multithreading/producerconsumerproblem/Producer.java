package com.ojas.multithreading.producerconsumerproblem;

public class Producer implements Runnable {
    private Resource sharedBuffer;

    public Producer(Resource resource) {
        this.sharedBuffer = resource;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                sharedBuffer.produceItem(new Resource.Item(i, System.currentTimeMillis()));
            }
        } catch (Exception e) {
            throw new RuntimeException("failed to produce item");
        }
    }


}
