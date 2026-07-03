package com.ojas.multithreading.customlocks.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    private boolean isAvailable = false;

    public void produce(ReentrantLock lock) {
        try {
            lock.lock();
            System.out.println("Thread acquired by " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // ... handling some exceptions
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("Lock released by " + Thread.currentThread().getName());
            lock.unlock();
        }
    }
}
