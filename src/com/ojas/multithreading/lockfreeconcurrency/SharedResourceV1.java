package com.ojas.multithreading.lockfreeconcurrency;

public class SharedResourceV1 {
    int counter = 0;

    // non thread safe & not atomic
    public void increment() {
        counter++; // essentially 3 steps: 1. read, 2. increment, 3. write
    }

    public int getCounter() {
        return counter;
    }
}
