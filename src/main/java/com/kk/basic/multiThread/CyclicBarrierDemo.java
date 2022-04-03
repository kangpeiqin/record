package com.kk.basic.multiThread;

import java.util.concurrent.CyclicBarrier;

/**
 * @author KPQ
 * @date 2022/1/26
 */
public class CyclicBarrierDemo {

    static class Tourist extends Thread {
        private CyclicBarrier barrier;

        public Tourist(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((int) ((Math.random()) * 1000));
                barrier.await();
                System.out.println(this.getName() + "arrive A" + System.currentTimeMillis());
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        final int num = 13;
        Tourist[] threads = new Tourist[num];
        CyclicBarrier barrier = new CyclicBarrier(num, () -> {
            System.out.println("all arrived" + System.currentTimeMillis());
        });
        for (int i = 0; i < num; i++) {
            threads[i] = new Tourist(barrier);
            threads[i].start();
        }

    }

}
