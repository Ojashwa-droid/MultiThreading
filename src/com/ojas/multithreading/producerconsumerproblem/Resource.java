package com.ojas.multithreading.producerconsumerproblem;

import java.util.LinkedList;
import java.util.Queue;

public class Resource {

    private Queue<Item> sharedBuffer;
    private int bufferSize;

    public Resource(int bufferSize) {
        this.sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public synchronized void produceItem(Item item) {
        while (sharedBuffer.size() == bufferSize) {
            try {
                System.out.println("Queue is full. Producer is waiting...");
                wait(); // releases the monitor lock
            } catch (InterruptedException e) {
                // TODO: handle exception
                throw new RuntimeException(e);
            }
        }

        sharedBuffer.add(item);
        notify();
        System.out.println("Produced item: " + item.toString() + " and added it to the buffer.");
    }

    public synchronized Item consumeItem() {
        while (sharedBuffer.isEmpty()) {
            try {
                System.out.println("Queue is empty. Consumer is waiting...");
                wait(); // releases the monitor lock
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Item item = sharedBuffer.poll();
        System.out.println("Consumed item: " + item.toString());

        notify();
        return item;
    }

    public static class Item {
        private int value;
        private long timestamp;

        public Item(int value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }

        public String toString() {
            return "Item [value=" + value + ", timestamp=" + timestamp + "]";
        }
    }
}
