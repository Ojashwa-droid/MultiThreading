package com.ojas.multithreading.sharedresource.v2;

public class Test {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread producerThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sharedResource.addItem();
        });

        Thread consumerThread = new Thread(() -> {
            sharedResource.consumeItem();
        });

        producerThread.start();
        consumerThread.start();
    }
}
