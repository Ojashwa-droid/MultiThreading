package com.ojas.multithreading.deadlock;

public class Test {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread task1 = new Thread(new Task1(paper, pen));
        Thread task2 = new Thread(new Task2(paper, pen));

        task1.start();
        task2.start();
    }
}

class Task1 implements Runnable {
    private final Paper paper;
    private final Pen pen;

    public Task1(Paper paper, Pen pen) {
        this.paper = paper;
        this.pen = pen;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        // task1 thread will try to get pen first & paper second
        // eventually will lead to a deadlock as pen is already locked by task2 thread
        pen.writeWithPenAndPaper(paper);
    }
}

class Task2 implements Runnable {
    private final Paper paper;
    private final Pen pen;

    public Task2(Paper paper, Pen pen) {
        this.paper = paper;
        this.pen = pen;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        // tries to get paper first & pen second
        // eventually will lead to a deadlock as paper is already locked by task1 thread

        synchronized (pen) {
            paper.writeWithPaperAndPen(pen);
        }
    }
}
