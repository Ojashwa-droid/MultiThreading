package com.ojas.multithreading.threadpool.forkjoinpool;

import java.util.concurrent.RecursiveTask;

public class ComputeSumTask extends RecursiveTask<Integer> {

    int start;
    int end;

    public ComputeSumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Integer compute() {
        if (end - start <= 4) {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // divide the task into smaller task
            int mid = (start + end) / 2;
            ComputeSumTask leftTask = new ComputeSumTask(start, mid);
            ComputeSumTask rightTask = new ComputeSumTask(mid + 1, end);

            // now, fork the sub-tasks for parallel execution
            leftTask.fork();
            rightTask.fork();

            // combine the result for both sub-tasks
            return leftTask.join() + rightTask.join();
        }
    }
}
