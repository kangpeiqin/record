package com.kk.basic.multiThread;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author KPQ
 * @date 2022/2/7
 */
public class ThreadPoolTest {
    static class MyRunnable implements Runnable {
        private String command;

        public MyRunnable(String s) {
            this.command = s;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
            process();
            System.out.println(Thread.currentThread().getName() + " END Time = " + new Date());
        }

        private void process() {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 1000; i++) {
            Runnable worker = new MyRunnable("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        System.out.println("finish");
    }
}
