package com.ojas.multithreading.moniter;

public class MoniterLock {
    public synchronized void task1() {
        // do something
        try {
            System.out.println("Inside task1");
            Thread.sleep(10000);
            System.out.println("Task1 complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void task2() {
        System.out.println("Inside task2, before sync");

        synchronized (this) {
            System.out.println("Inside task2, after sync");
        }
    }

    public void task3() {
        System.out.println("Inside task3");
    }
}
