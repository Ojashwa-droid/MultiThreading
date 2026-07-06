package com.ojas.multithreading.producerconsumerproblem.multiple;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerWithArrayBlockingQueue {
    // A simple record to hold our data (replaces your static class Item)
    record Item(int id, int value) {}

    public static void main(String[] args) {

        // 1. THIS REPLACES THE ENTIRE RESOURCE CLASS!
        // A thread-safe queue with a strict capacity of 3.
        BlockingQueue<Item> sharedBuffer = new ArrayBlockingQueue<>(3);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Random random = new Random();

        // 2. Producers
        for (int i = 0; i < 5; i++) {
            int identifier = i;
            executorService.submit(() -> {
                try {
                    Item newItem = new Item(identifier, identifier + random.nextInt(100));

                    // put() automatically acquires the lock, checks if full,
                    // awaits the 'notFull' condition, adds the item,
                    // signals 'notEmpty', and unlocks!
                    sharedBuffer.put(newItem);

                    System.out.println("Produced: " + newItem);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 3. Consumers
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    // take() automatically acquires the lock, checks if empty,
                    // awaits the 'notEmpty' condition, removes the item,
                    // signals 'notFull', and unlocks!
                    Item consumedItem = sharedBuffer.take();

                    System.out.println("Consumed: " + consumedItem);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executorService.shutdown();
    }
}
