package com.ojas.multithreading.threadpool.completablefuture;

import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        executor.allowCoreThreadTimeOut(true);

        // 1. CompletableFuture.supplyAsync()
        // 2. CompletableFuture.thenApplyAsync() & CompletableFuture.thenApply()


/*        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            // task being executed
            try {
                System.out.println("Thread Name for supplyAsync(): " + Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Ojashwa ";
        }, executor);

        CompletableFuture<String> completableFuture2 = completableFuture1.thenApplyAsync((String str) -> {
            // task being executed
            System.out.println("Thread Name for thenApply(): " + Thread.currentThread().getName());
            String string = str + "Tripathi";
            return string;
        });*/


        // 3. CompletableFuture.thenCompose() & CompletableFuture.thenComposeAsync)
/*
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                    // task being executed
                    try {
                        System.out.println("Thread Name for supplyAsync(): " + Thread.currentThread().getName());
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Ojashwa ";
                }, executor)
                .thenCompose((String str) -> {
                    // task being completed
                    System.out.println("Thread Name for 1st thenCompose(): " + Thread.currentThread().getName());
                    return CompletableFuture.supplyAsync(() -> {
                        return str + "Tripathi";
                    });
                })
                .thenCompose((String str) -> {
                    // task being completed
                    System.out.println("Thread Name for 2nd thenCompose(): " + Thread.currentThread().getName());
                    return CompletableFuture.supplyAsync(() -> {
                        return str + " is Great";
                    });
                });*/

        // 4. CompletableFuture.thenAcceptAsync() & CompletableFuture.thenAccept()

/*        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            // task being executed
            try {
                System.out.println("Thread Name for supplyAsync(): " + Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Ojashwa ";
        }, executor);

        CompletableFuture<Void> voidCompletableFuture = completableFuture.thenAcceptAsync((String str) -> {
            System.out.println("Thread Name for thenAccept(): " + Thread.currentThread().getName());
            System.out.println("All done");
        }, executor);

        System.out.println(voidCompletableFuture.get());*/

        // 5. CompletableFuture.thenCombineAsync() & CompletableFuture.thenCombine( )

        CompletableFuture<Double> asyncTask1 = CompletableFuture.supplyAsync(() -> {
            return Math.random() * 100;
        }, executor);

        CompletableFuture<String> asyncTask2 = CompletableFuture.supplyAsync(() -> {
            return " Ojashwa";
        }, executor);

        CompletableFuture<String> combinedAsync = asyncTask1.thenCombine(asyncTask2, (r1, r2) -> {
            return r1 + r2;
        });

        String res = combinedAsync.get();
        System.out.println(res);

        executor.shutdown();
    }
}
