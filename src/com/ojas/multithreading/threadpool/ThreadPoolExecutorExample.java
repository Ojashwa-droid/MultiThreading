package com.ojas.multithreading.threadpool;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) {

        System.out.println("Main thread : " + Thread.currentThread().getName() + " started");

        try (ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 5, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2), new CustomThreadFactory(), new CustomRejectHandler())) {

            executor.allowCoreThreadTimeOut(true);

            for (int i = 0; i < 7; i++) {
                int nthTask = i;
                executor.submit(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // ...handle some exceptions
                        throw new RuntimeException(e);
                    }
                    System.out.println("Task " + nthTask + " executed by " + Thread.currentThread().getName());
                });
            }

            executor.shutdown();
        }
    }


}

class CustomThreadFactory implements ThreadFactory {

    /**
     * Constructs a new unstarted {@code Thread} to run the given runnable.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     * @see <a href="../../lang/Thread.html#inheritance">Inheritance when
     * creating threads</a>
     */
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.setDaemon(false);
        return thread;
    }
}

class CustomRejectHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task rejected: " + r.toString());
/*        BlockingQueue<Runnable> queue = executor.getQueue();
        if (!executor.isShutdown() && !queue.isEmpty()) {
            queue.poll();
            executor.execute(r);
        }

        // similar logic can be written in the custom reject handler
*/
    }
}
