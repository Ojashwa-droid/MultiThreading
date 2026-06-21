package com.ojas.multithreading.producerconsumerproblem;

public class Main {
    public static void main(String[] args) {
        Resource resource = new Resource(3);

        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);

        Thread producerThread = new Thread(producer, "ProducerThread");
        Thread consumerThread = new Thread(consumer, "ConsumerThread");

        producerThread.start();
        consumerThread.start();
    }
}
