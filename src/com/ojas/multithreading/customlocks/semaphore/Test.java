package com.ojas.multithreading.customlocks.semaphore;

public class Test {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(sharedResource::produce); // using method reference to replace the lambda exp

        Thread thread2 = new Thread(() -> {
            sharedResource.produce();
        });

        Thread thread3 = new Thread(() -> {
            sharedResource.produce();
        });

        Thread thread4 = new Thread(() -> {
            sharedResource.produce();
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
