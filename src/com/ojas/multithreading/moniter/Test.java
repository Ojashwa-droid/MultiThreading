package com.ojas.multithreading.moniter;

public class Test {
    public static void main(String[] args) {
        MoniterLock example = new MoniterLock();

        MonitorThread1 moniterThread1 = new MonitorThread1(example);

        Thread thread1 = new Thread(moniterThread1);
        Thread thread2 = new Thread(() -> {example.task2();});
        Thread thread3 = new Thread(() -> {example.task3();});

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
