package com.ojas.multithreading.threadpool.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        executor.allowCoreThreadTimeOut(true);

        // UseCase 1
        Future<?> futureObject1 = executor.submit(() -> {
            System.out.println("Task is running");
        });
        System.out.println("Is Done? : " + futureObject1.isDone());
        System.out.println(futureObject1.get());


        // UseCase 2
        List<Integer> integerList = new ArrayList<>();
        Future<List<Integer>> futureObject2 = executor.submit(new MyRunnable(integerList), integerList);

        List<Integer> list = futureObject2.get();
        System.out.println(list);


        // UseCase 3
        Future<List<Integer>> futureObject3 = executor.submit(() -> {
            List<Integer> list1 = new ArrayList<>();
            list1.add(1);
            return list1;
        });

        try {
            List<Integer> integerList1 = futureObject3.get();
            System.out.println(integerList1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


    }

    public static class MyRunnable implements Runnable {
        List<Integer> list;

        public MyRunnable(List<Integer> list) {
            this.list = list;
        }

        /**
         * Runs this operation.
         */
        @Override
        public void run() {
            list.add(200);
            // does some more work
        }
    }
}
