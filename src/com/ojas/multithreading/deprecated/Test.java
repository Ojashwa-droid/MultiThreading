package com.ojas.multithreading.deprecated;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        SharedResource resource = new SharedResource();

        System.out.println("Main thread started");

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " calling produce method");
            resource.produce();
        });
        thread1.setDaemon(true); // daemon thread
        thread1.start();

        System.out.println("Main thread has finished its execution");
    }
}
