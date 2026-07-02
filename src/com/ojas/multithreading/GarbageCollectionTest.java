package com.ojas.multithreading;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class GarbageCollectionTest {

    public static void main(String[] args) {
        Person person = new Person("Ojashwa", 22);
        ReferenceQueue<Person> personReferenceQueue = new ReferenceQueue<>();
        PhantomReference<Person> phantomReference = new PhantomReference<>(person, personReferenceQueue);

        person = null;
        System.gc();

        if (personReferenceQueue.poll() != null) {
            System.out.println("Object has been reclaimed by the garbage collector.");
        } else {
            System.out.println("Object has not been collected yet.");
        }

    }

    private static class Person{
        private String name;
        private int age;

        public Person() {}

        public Person(String name) {
          this.name = name;
          this.age = 0;
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
