package com.ojas.multithreading.customlocks.semaphore;

import java.util.concurrent.Semaphore;

public class SharedResource {
    private boolean isAvailable = false;
    private static final Semaphore semaphore = new Semaphore(2);

    static {
        int availablePermits = semaphore.availablePermits();
        System.out.println("Total Available permits: " + availablePermits);
    }

    public void produce() {
        try {
            semaphore.acquire();
            System.out.println("Lock acquired by " + Thread.currentThread().getName());
            System.out.println("Available permits: " + semaphore.availablePermits());
            isAvailable = true;
            Thread.sleep(5000);
        } catch (Exception e) {
            // ... handle some exceptions
            throw new RuntimeException(e);
        } finally {
            System.out.println("Lock released by " + Thread.currentThread().getName());
            semaphore.release();
            System.out.println("Available permits: " + semaphore.availablePermits());
        }
    }
}
