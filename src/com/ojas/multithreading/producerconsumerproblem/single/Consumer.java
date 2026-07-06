package com.ojas.multithreading.producerconsumerproblem.single;

public class Consumer implements Runnable {
    private final Resource sharedBuffer;

    public Consumer(Resource resource) {
        this.sharedBuffer = resource;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                sharedBuffer.consumeItem();
            }
        } catch (Exception e) {
            throw new RuntimeException("failed to consume item");
        }
    }
}
