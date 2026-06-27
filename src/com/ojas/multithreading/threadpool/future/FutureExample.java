package com.ojas.multithreading.threadpool.future;

import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        Future<?> futureObject = executor.submit(() -> {
            try {
                Thread.sleep(7000);
                System.out.println("Task is running");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        /*
         caller thread wants to know the status, result or handle exceptions to the submitted task
         ...use future interface to perform above-mentioned actions.
        */

        System.out.println("Is Done? : " + futureObject.isDone());

        try {
            futureObject.get(2, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("TimeoutException happened");
        } catch (Exception e) {
            // handle exception
        }

        try {
            Object object = futureObject.get();
            System.out.println(object);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();
    }
}
