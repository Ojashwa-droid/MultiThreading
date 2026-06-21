package com.ojas.multithreading.moniter;

public class MonitorThread1 implements Runnable{
    MoniterLockExample obj;

    public MonitorThread1(MoniterLockExample obj) {
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
