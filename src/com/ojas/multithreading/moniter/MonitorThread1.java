package com.ojas.multithreading.moniter;

public class MonitorThread1 implements Runnable{
    MoniterLock obj;

    public MonitorThread1(MoniterLock obj) {
        this.obj = obj;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        obj.task1();
    }
}
