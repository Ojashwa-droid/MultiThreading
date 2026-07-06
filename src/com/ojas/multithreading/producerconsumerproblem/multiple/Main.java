package com.ojas.multithreading.producerconsumerproblem.multiple;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Resource sharedResource = new Resource(3);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int identifer = i;
            executorService.submit(() -> {
                try {
                    sharedResource.produceItem(new Resource.Item(identifer, identifer + random.nextInt(100)));
                } catch (Exception e) {
                    throw new RuntimeException("failed to produce item");
                }
            });
        }

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    sharedResource.consumeItem();
                } catch (Exception e) {
                    throw new RuntimeException("failed to consume item");
                }
            });
        }

        executorService.shutdown();
    }
}
