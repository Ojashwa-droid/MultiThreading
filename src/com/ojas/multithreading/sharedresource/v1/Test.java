package com.ojas.multithreading.sharedresource.v1;

public class Test {
    public static void main(String[] args) {
        System.out.println("Main method start.");

        SharedResource resource = new SharedResource();

        // producer thread
        ProduceTask produceTask = new ProduceTask(resource);
        Thread producerThread = new Thread(produceTask, "Producer");

        // consumer thread
        ConsumeTask consumeTask = new ConsumeTask(resource);
        Thread consumerThread = new Thread(consumeTask, "Consumer");


        // both the threads are in "RUNNABLE" state
        producerThread.start();
        consumerThread.start();

        System.out.println("Main method end.");
    }
}
