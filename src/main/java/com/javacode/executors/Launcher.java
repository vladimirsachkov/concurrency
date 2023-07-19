package com.javacode.executors;

import static com.javacode.threadslesson.ColorScheme.RED;

public class Launcher {
    private static final int POOL_SIZE = 2;

    public static void main(String[] args) throws InterruptedException {
        boolean isDaemon = true;

        System.out.println(RED+"Starting main thread");
        GCDRunnable r = new GCDRunnable(isDaemon);
        runInOneThread(r, isDaemon);
        Thread.sleep(100);
        System.out.println(RED+"Leaving the main thread");
    }

    private static void runInOneThread(GCDRunnable r, boolean isDaemon) throws InterruptedException {
        Thread th = new Thread(r);
        if (isDaemon) {
            th.setDaemon(true);
        }
        Thread.sleep(100);
        th.start();
//        Thread.sleep(100);
//        th.interrupt();

    }
}
