package com.ojas.multithreading.fairness;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairUnfairLockExample {
   // private final Lock unfairLock = new ReentrantLock(); // unfairLock is unfair by default
    private final Lock fairLock = new ReentrantLock(true); // fairLock is fair

    public void accessSharedResource() {
        //unfairLock.lock();
        fairLock.lock();
        try {
            // Access shared resource
            System.out.println(Thread.currentThread().getName() + " is accessing shared resource");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // ... handle some exceptions
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " is releasing lock");
            fairLock.unlock();
            //unfairLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FairUnfairLockExample example = new FairUnfairLockExample();

        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {
                example.accessSharedResource();
            }
        };

        Thread thread1 = new Thread(runnableTask, "Thread 1");
        Thread thread2 = new Thread(runnableTask, "Thread 2");
        Thread thread3 = new Thread(runnableTask, "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Main thread is exiting.");
    }


}
