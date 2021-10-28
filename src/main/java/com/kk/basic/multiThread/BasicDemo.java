package com.kk.basic.multiThread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author KPQ
 * @date 2021/10/28
 */
public class BasicDemo {

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            int sleepSeconds = new Random().nextInt(1000);
            Thread.sleep(sleepSeconds);
            return sleepSeconds;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(new Task());
        Thread.sleep(100);
        try {
            System.out.println(future.get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
