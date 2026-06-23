package com.ojas.multithreading.lockfreeconcurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedResourceV2 {
    AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }
}
