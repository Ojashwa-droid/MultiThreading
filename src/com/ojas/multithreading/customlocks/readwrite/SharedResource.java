package com.ojas.multithreading.customlocks.readwrite;

import java.util.concurrent.locks.ReadWriteLock;

public class SharedResource {
    private boolean isAvailable = false;

    public void produce(ReadWriteLock readWriteLock) {
        try {
            readWriteLock.readLock().lock();
            System.out.println("Read lock acquired by " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            // ... handling some exceptions
            throw new RuntimeException(e);
        } finally {
            System.out.println("Read lock released by " + Thread.currentThread().getName());
            readWriteLock.readLock().unlock();
        }
    }

    public void consume(ReadWriteLock readWriteLock) {

        System.out.println(Thread.currentThread().getName() + " is trying to write");

        try {
            readWriteLock.writeLock().lock();
            System.out.println("Write lock acquired by " + Thread.currentThread().getName());
            isAvailable = false;
        } catch (Exception e) {
            // ... handling some exceptions
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("Write lock released by " + Thread.currentThread().getName());
            readWriteLock.writeLock().unlock();
        }

    }
}
