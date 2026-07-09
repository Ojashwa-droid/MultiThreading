package com.ojas.multithreading.scattergatherproblem;

import java.util.*;
import java.util.concurrent.*;

public class ProductService {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private static final String URL = "http://localhost:8080/";


    // Method 1: Using CountDownLatch
    public Set<Integer> fetchProductPricesV1() throws ExecutionException, InterruptedException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());
        CountDownLatch countDownLatch = new CountDownLatch(4);

        executorService.submit(new PriceGatherTaskV1(URL, 1, prices, countDownLatch));
        executorService.submit(new PriceGatherTaskV1(URL, 2, prices, countDownLatch));
        executorService.submit(new PriceGatherTaskV1(URL, 3, prices, countDownLatch));
        executorService.submit(new PriceGatherTaskV1(URL, 4, prices, countDownLatch));

        countDownLatch.await(3, TimeUnit.SECONDS);

        /*
         *  But using thread.sleep() is not a good practice & not an efficient solution,
         *  in case the worker threads complete their tasks before the sleep time.
         *  The main thread will wait for extra time even after the worker threads complete their tasks.
         *   Thread.sleep(3000);
         */

        return prices;
    }

    // Method 2: Using CompletableFuture
    public Set<Integer> fetchProductPricesV2() throws ExecutionException, InterruptedException, TimeoutException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());
        CompletableFuture<Void> task1 =
                CompletableFuture.runAsync(new PriceGatherTaskV2(URL, 1, prices), executorService);
        CompletableFuture<Void> task2 =
                CompletableFuture.runAsync(new PriceGatherTaskV2(URL, 2, prices), executorService);
        CompletableFuture<Void> task3 =
                CompletableFuture.runAsync(new PriceGatherTaskV2(URL, 3, prices), executorService);
        CompletableFuture<Void> task4 =
                CompletableFuture.runAsync(new PriceGatherTaskV2(URL, 4, prices), executorService);

        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(task1, task2, task3, task4);
        completableFuture.get(3, TimeUnit.SECONDS);

        return prices;
    }

    private static class PriceGatherTaskV1 implements Runnable {
        private String url;
        private Integer productId;
        private Set<Integer> prices;
        private CountDownLatch countDownLatch;

        public PriceGatherTaskV1(String url, Integer productId, Set<Integer> prices, CountDownLatch countDownLatch) {
            this.url = url;
            this.productId = productId;
            this.prices = prices;
            this.countDownLatch = countDownLatch;
        }

        /**
         * Runs this operation.
         */
        @Override
        public void run() {
            // ...simulate the http call
            // ...parse the response
            // ...update the price
            System.out.println("Fetching price for product " + productId + " on " + url);
            prices.add(new Random().nextInt(100));
            countDownLatch.countDown();
        }
    }

    private static class PriceGatherTaskV2 implements Runnable {
        private String url;
        private Integer productId;
        private Set<Integer> prices;

        public PriceGatherTaskV2(String url, Integer productId, Set<Integer> prices) {
            this.url = url;
            this.productId = productId;
            this.prices = prices;
        }

        /**
         * Runs this operation.
         */
        @Override
        public void run() {
            // ...simulate the http call
            // ...parse the response
            // ...update the price
            System.out.println("Fetching price for product " + productId + " on " + url);
            prices.add(new Random().nextInt(100));
        }
    }
}
