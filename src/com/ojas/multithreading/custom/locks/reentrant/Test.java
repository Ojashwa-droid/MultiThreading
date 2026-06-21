package com.ojas.multithreading.custom.locks.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        SharedResource sharedResource1 = new SharedResource();
        SharedResource sharedResource2 = new SharedResource();

        ReentrantLock reentrantLock = new ReentrantLock(true);

        Thread thread1 = new Thread(() -> {
            sharedResource1.produce(reentrantLock);
        });

        Thread thread2 = new Thread(() -> {
            sharedResource2.produce(reentrantLock);
        });

        thread1.start();
        thread2.start();
    }
}
