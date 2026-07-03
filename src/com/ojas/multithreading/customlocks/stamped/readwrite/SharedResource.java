package com.ojas.multithreading.customlocks.stamped.readwrite;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {
    private boolean isAvailable = false;
    StampedLock stampedLock = new StampedLock();

    public void produce() {
        long stamp = stampedLock.readLock();
        try {
            System.out.println("Read lock acquired by "  + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // ... handling some exceptions
            e.printStackTrace();
        } finally {
            System.out.println("Read lock released by " + Thread.currentThread().getName());
            stampedLock.unlockRead(stamp);
        }
    }

    public void consume() {
        long stamp = stampedLock.writeLock();
        try {
            System.out.println("Write lock acquired by " + Thread.currentThread().getName());
            isAvailable = false;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Write lock released by " + Thread.currentThread().getName());
            stampedLock.unlockWrite(stamp);
        }
    }
}
