package com.ojas.multithreading.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalExample {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();



/*        // setting the thread-local variable for the main thread
        threadLocal.set(Thread.currentThread().getName());

        Thread thread = new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " is running");
            threadLocal.set(Thread.currentThread().getName());
            System.out.println("Thread " + Thread.currentThread().getName() + " has thread-local variable " + threadLocal.get());
        });

        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Thread " + Thread.currentThread().getName() + " has thread-local variable " + threadLocal.get());
*/
        // we need to clean up the thread-local variable
        // Scenario - ThreadPool

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(() -> {
            threadLocal.set(Thread.currentThread().getName());
        });

        for (int i = 0; i < 15; i++) {
            executorService.submit(() -> {
                System.out.println(threadLocal.get());
                threadLocal.remove();
            });
        }
    }
}
