package com.ojas.multithreading.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalExample3 {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        ExecutorService service = Executors.newFixedThreadPool(10);

        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            service.submit(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                System.out.println(threadLocal.get().format(new Date()));
                countDownLatch.countDown();
            });
        }
        threadLocal.remove();
        System.out.println(threadLocal.get().format(new Date()));

        countDownLatch.await();
        service.shutdown();
    }
}
