package com.ojas.multithreading.threadpool.synchronizationaids;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

public class PhaserAsACyclicBarrier {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Phaser phaser = getPhaser();

        executor.submit(new Task(phaser));
        executor.submit(new Task(phaser));
        executor.submit(new Task(phaser));

        executor.shutdown();
        System.out.println(Thread.currentThread().getName() + " has completed its execution");

    }

    private static Phaser getPhaser() {
        int phaseCount = 3;

        // Return true to terminate the phaser if we've reached our target phase (0, 1, 2)
        // Returning true causes isTerminated() to return true for the worker threads.
        return new Phaser(3) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("--- Phase " + phase + " completed! Parties: " + registeredParties + " ---");

                // Return true to terminate the phaser if we've reached our target phase (0, 1, 2)
                // Returning true causes isTerminated() to return true for the worker threads.
                return phase >= phaseCount - 1 || registeredParties == 0;
            }
        };
    }

    private static class Task implements Runnable {
        private final Phaser phaser;
        private AtomicInteger count = new AtomicInteger(0);

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        /**
         * Runs this operation.
         */
        @Override
        public void run() {
            while (!phaser.isTerminated()) {
                System.out.println(Thread.currentThread().getName() + " is processing phase: " + phaser.getPhase());

                try {
                    Thread.sleep(1000); // Simulating work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName() + " arrived at the barrier");
                phaser.arriveAndAwaitAdvance();
            }

            System.out.println(Thread.currentThread().getName() + " has completed all phases and is exiting.");
        }
    }
}
