package com.ojas.multithreading.threadpool.shutdown;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutdownExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Task completed");
            });
        }

        List<Runnable> runnables = executorService.shutdownNow();
        System.out.println(runnables.size());

        try {
            boolean isTerminated = executorService.awaitTermination(6, TimeUnit.SECONDS);
            System.out.println("Is terminated: " + isTerminated);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main thread is done");
    }
}
