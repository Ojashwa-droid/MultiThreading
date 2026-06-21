package com.ojas.multithreading.custom.locks.stamped.optimistic;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {
    private int a = 10;
    private final StampedLock stampedLock = new StampedLock();

    public void produce() {
        long stamp = stampedLock.tryOptimisticRead();

        try {
            System.out.println("Optimistic lock acquired by " + Thread.currentThread().getName());
            a = a + 100;
            Thread.sleep(6000);

            if (stampedLock.validate(stamp)) {
                System.out.println("Optimistic lock validated by " + Thread.currentThread().getName() + " making changes successfully");
            } else {
                System.out.println("Optimistic lock failed by " + Thread.currentThread().getName() + " and rolling back");
                a = a - 100;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void consume() {
        long stamp = stampedLock.writeLock();
        System.out.println("Write lock acquired by " + Thread.currentThread().getName());

        try {
            a = 25;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Write lock released by " + Thread.currentThread().getName());
            stampedLock.unlockWrite(stamp);
        }
    }
}
