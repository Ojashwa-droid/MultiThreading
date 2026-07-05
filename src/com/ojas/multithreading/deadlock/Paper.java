package com.ojas.multithreading.deadlock;

public class Paper {

    public synchronized void writeWithPaperAndPen(Pen pen) {
        System.out.println(Thread.currentThread().getName() + " is using paper " + this + " & trying to get pen " + pen);
        pen.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " is finished writing using paper " + this);
    }

}


