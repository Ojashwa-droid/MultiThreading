package com.ojas.multithreading.threadpool.scheduledthreadpool;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        // 1. schedule a task to run after 5 seconds

/*        executorService.schedule(() -> {
            System.out.println("Hello world!");
        }, 5, TimeUnit.SECONDS);

        executorService.shutdown();
 */

        // 2. schedule a task to run after 5 seconds and then repeat every 3 seconds
        // scheduleAtFixedRate will not stop the task if it takes more than the specified delay
        // it will start the next task as soon as the previous task is completed
        // tasks wait in the submission queue and await execution

 /*       ScheduledFuture<?> scheduledFuture = executorService.scheduleAtFixedRate(() -> {
            System.out.println("Hello world!");
        }, 5, 3, TimeUnit.SECONDS);

        try {
            Thread.sleep(20000);
            scheduledFuture.cancel(true);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
*/

        // 3. scheduleTaskWithFixedDelay()
        // scheduleWithFixedDelay will stop the task if it takes more than the specified delay
        // it will start the next task after the previous task is completed
        // it will not stop the task if it takes less than the specified delay

//        AtomicInteger counter = new AtomicInteger(0);
//        ScheduledFuture<?> scheduledFuture = executorService.scheduleWithFixedDelay(() -> {
//            counter.getAndIncrement();
//            System.out.println("Thread picked up a task");
//            try {
//                Thread.sleep(6000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("Task completed");
//        }, 3, 3, TimeUnit.SECONDS);
//
//        try {
//            Thread.sleep(20000);
//            boolean cancelled = scheduledFuture.cancel(true);
//            System.out.println("Task cancelled: " + cancelled);
//            System.out.println("Counter: " + counter.get());
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        executorService.shutdownNow();
//        System.out.println("Main thread is completed");
    }
}
