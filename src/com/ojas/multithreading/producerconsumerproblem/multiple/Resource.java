package com.ojas.multithreading.producerconsumerproblem.multiple;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource {
    private final Queue<Item> sharedBuffer;
    private final int bufferSize;

    private final Lock lock = new ReentrantLock();
    private final Condition producerWaitSet = lock.newCondition();
    private final Condition consumerWaitSet = lock.newCondition();

    public Resource(int bufferSize) {
        this.sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public void produceItem(Item item) {
        lock.lock();
        System.out.println("Producer " + Thread.currentThread().getName() + " has acquired lock");

        try {
            while (sharedBuffer.size() == bufferSize) {
                System.out.println("Buffer is full, producer thread is waiting");
                producerWaitSet.await();
            }

            sharedBuffer.add(item);
            System.out.println("Producer " + Thread.currentThread().getName() +
                    " has produced item: " + item.toString() + " and released lock");
            consumerWaitSet.signal();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("failed to produce item");
        } finally {
            lock.unlock();
        }

    }

    public void consumeItem() {
        lock.lock();
        System.out.println("Consumer " + Thread.currentThread().getName() + " has acquired lock");
        try {
            while (sharedBuffer.isEmpty()) {
                System.out.println("Buffer is empty, consumer thread is waiting");
                consumerWaitSet.await();
            }

            Item item = sharedBuffer.poll();
            System.out.println("Consumer " + Thread.currentThread().getName() +
                    " has consumed item: " + item.toString() + " and released lock");
            producerWaitSet.signal();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("failed to consume item");
        } finally {
            lock.unlock();
        }
    }


    static class Item {
        private final int id;
        private final int value;

        public Item(int id, int value) {
            this.id = id;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    ", value=" + value +
                    '}';
        }
    }
}
