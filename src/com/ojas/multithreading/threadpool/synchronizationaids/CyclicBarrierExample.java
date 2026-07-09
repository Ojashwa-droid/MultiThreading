package com.ojas.multithreading.threadpool.synchronizationaids;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CyclicBarrier barrier = new CyclicBarrier(4);

        executorService.submit(new Task(barrier));
        executorService.submit(new Task(barrier));
        executorService.submit(new Task(barrier));
        executorService.submit(new Task(barrier));

        executorService.shutdown();
        System.out.println("Main thread is done");
    }

    private static class Task implements Runnable {
        private final CyclicBarrier barrier;
        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        /**
         * Runs this operation.
         */
        @Override
        public void run() {
            while (true) {
               try {
                   System.out.println("Task is running");
                   Thread.sleep(1000);
                   barrier.await();
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }
               // ...send the msg to the next service simultaneously
                System.out.println("Task is done");
            }
        }
    }
}
