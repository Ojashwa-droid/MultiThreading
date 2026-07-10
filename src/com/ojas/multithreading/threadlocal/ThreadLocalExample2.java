package com.ojas.multithreading.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalExample2 {

    public static void main(String[] args) {
        ThreadLocalDateFormater dateFormater = new ThreadLocalDateFormater();
        ThreadLocal<SimpleDateFormat> formatter = ThreadLocalDateFormater.formatter;

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(formatter.get().format(new Date()));
            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ThreadLocalDateFormater.formatter.remove();
        System.out.println("Main thread exiting");
    }

    private static class ThreadLocalDateFormater {
        private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<>(){
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }

            /**
             * Returns the value in the current thread's copy of this
             * thread-local variable.  If the variable has no value for the
             * current thread, it is first initialized to the value returned
             * by an invocation of the {@link #initialValue} method.
             *
             * @return the current thread's value of this thread-local
             */
            @Override
            public SimpleDateFormat get() {
                return super.get();
            }
        };


    }
}


