package com.javacode.vilatilelesson;

import static com.javacode.threadslesson.ColorScheme.GREEN;
import static com.javacode.threadslesson.ColorScheme.RED;

public class VolatileMain {

    private static volatile int counter;

    public static void main(String[] args) {
        new SimpleWriter().start();
        new SimpleReader().start();
    }

    private static class SimpleWriter extends Thread {

        @Override
        public void run() {
            int localCounter = counter;
            for (int i = 0; i < 10; i++) {
                System.out.println(GREEN + "Writer implements counter " + (localCounter + 1));
                counter = ++localCounter;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static class SimpleReader extends Thread {

        @Override
        public void run() {
            int localCounter = counter;
            while (localCounter < 10) {
                if (localCounter != counter) {
                        System.out.println(RED + "Reader reads counter " + counter);
                        localCounter = counter;
                }
            }
        }
    }
}
