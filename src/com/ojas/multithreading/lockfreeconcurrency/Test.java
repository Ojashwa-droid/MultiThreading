package com.ojas.multithreading.lockfreeconcurrency;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        // SharedResourceV1 sharedResource = new SharedResourceV1();
        SharedResourceV2 sharedResource = new SharedResourceV2();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                sharedResource.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                sharedResource.increment();
            }
        });

        thread1.start();
        thread2.start();

        // to indicate main thread to wait for the child threads to complete
        thread1.join();
        thread2.join();
        System.out.println(sharedResource.getCounter());
    }
}
