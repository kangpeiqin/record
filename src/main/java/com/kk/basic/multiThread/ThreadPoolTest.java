package com.kk.basic.multiThread;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(16);
        map.put("test", 1);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 6, 1L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new ThreadPoolExecutor.AbortPolicy());
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
