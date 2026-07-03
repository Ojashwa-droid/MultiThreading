package com.ojas.multithreading.customlocks.readwrite;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        Thread thread1 = new Thread(() -> sharedResource.produce(readWriteLock));
        Thread thread2 = new Thread(() -> sharedResource.produce(readWriteLock));

        SharedResource sharedResource1 = new SharedResource();
        Thread thread3 = new Thread(() -> sharedResource1.consume(readWriteLock));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}