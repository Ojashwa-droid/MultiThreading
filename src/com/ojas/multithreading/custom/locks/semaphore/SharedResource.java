package com.ojas.multithreading.custom.locks.semaphore;

import java.util.concurrent.Semaphore;

public class SharedResource {
    private boolean isAvailable = false;
    Semaphore semaphore = new Semaphore(2);

    public void produce() {
        try {
            semaphore.acquire();
            System.out.println("Lock acquired by " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(5000);
        } catch (Exception e) {
            // ... handle some exceptions
            throw new RuntimeException(e);
        } finally {
            System.out.println("Lock released by " + Thread.currentThread().getName());
            semaphore.release();
        }
    }
}
