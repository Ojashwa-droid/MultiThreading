package com.ojas.multithreading.deprecated;

public class SharedResource {
    private boolean itemAvaliable;

    public synchronized void produce() {
        System.out.println("Lock acquired by " + Thread.currentThread().getName());
        itemAvaliable = true;

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // handle some exceptions here
            throw new RuntimeException(e);
        }

        System.out.println("Lock released by " + Thread.currentThread().getName());
    }
}
