package com.ojas.multithreading.threadpool.synchronizationaids;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class CountDownLatchExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch = new CountDownLatch(4);

        Stream.of(1, 2, 3, 4).forEach(i -> {
             executor.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is running the task " + i);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println("Main thread is done");
    }
}
