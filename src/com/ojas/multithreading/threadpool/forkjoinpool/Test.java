package com.ojas.multithreading.threadpool.forkjoinpool;

import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
/*        ExecutorService executorService = Executors.newWorkStealingPool(); // internally creates the fork-join-pool
        ForkJoinPool forkJoinPool = (ForkJoinPool) executorService;
*/

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ForkJoinTask<Integer> taskOutput = forkJoinPool.submit(new ComputeSumTask(1, 1000));

        Integer result = taskOutput.get();
        System.out.println("Result: " + result);
        forkJoinPool.shutdown();
    }
}
