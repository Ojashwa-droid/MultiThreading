package com.ojas.multithreading.lockfreeconcurrency;

public class VolatileExample {

    private volatile static boolean keepLooping = true;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Main thread started");

        Thread loopThread = new Thread(() -> {
            System.out.println("Loop thread started");
            long count = 0;
            while (keepLooping) {
                count++;
            }

/*            JIT compiler optimization for above while loop causes infinite loop even when "main" thread alters the value
               of keepLooping variable.

                if (keepLooping) {
                    while (true){
                        count++;
                    }
                }
*/

            System.out.println("Loop thread count: " + count);
        });

        loopThread.start();
        Thread.sleep(1000);
        keepLooping = false;
        System.out.println("Main thread set keepLooping to false");

        loopThread.join();
        System.out.println("Main thread finished");
    }
}
