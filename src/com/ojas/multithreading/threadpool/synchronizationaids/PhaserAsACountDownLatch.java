package com.ojas.multithreading.threadpool.synchronizationaids;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserAsACountDownLatch {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Phaser phaser = new Phaser(3);

        executor.submit(new Task(phaser));
        executor.submit(new Task(phaser));
        executor.submit(new Task(phaser));

        phaser.awaitAdvance(0);
        executor.shutdown();
        System.out.println(Thread.currentThread().getName() + " has completed its execution");

    }

    private static class Task implements Runnable {
        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        /**
         * Runs this operation.
         */
        @Override
        public void run() {
            // ...some initializations
            System.out.println(Thread.currentThread().getName() + " has started its execution");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " has completed its execution");
            phaser.arrive(); // similar to countDownLatch.countDown()

            // ...further processing
        }
    }
}
