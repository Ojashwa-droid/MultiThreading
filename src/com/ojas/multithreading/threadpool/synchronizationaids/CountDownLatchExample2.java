package com.ojas.multithreading.threadpool.synchronizationaids;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample2 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch = new CountDownLatch(4);

        executor.submit(new DependentService(countDownLatch));
        executor.submit(new DependentService(countDownLatch));
        executor.submit(new DependentService(countDownLatch));
        executor.submit(new DependentService(countDownLatch));

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        executor.shutdown();
        System.out.println("Main thread is done");
    }

    private static class DependentService implements Runnable {
        private final CountDownLatch countDownLatch;

        public DependentService(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        /**
         * Runs this operation.
         */
        @Override
        public void run() {
            try {
                System.out.println("Dependent service is running");
                Thread.sleep(3000);
                // ...some processing
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Dependent service is done");
                countDownLatch.countDown();
            }
        }
    }
}
