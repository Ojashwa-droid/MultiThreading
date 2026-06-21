package com.ojas.multithreading.sharedresource.v1;

public class ProduceTask implements Runnable {
    private SharedResource resource;

    public ProduceTask(SharedResource resource) {
        this.resource = resource;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        System.out.println("Producer thread: " + Thread.currentThread().getName() + " is running.");
        try {
            Thread.sleep(5000);
            resource.addItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
