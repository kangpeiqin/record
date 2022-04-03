package com.kk.basic.multiThread;

import java.util.concurrent.CountDownLatch;

/**
 * 1、countdown latch 它相当于是一个门栓，一开始是关闭的，所有希望通过该门的线程都需要等待，
 * 然后开始倒计时，倒计时变为0后，门栓打开，
 * 等待的所有线程都可以通过，它是一次性的，打开后就不能再关上了
 *
 * @author KPQ
 * @date 2022/1/26
 */
public class MultiThreadTest {

    static class Racer extends Thread {
        private CountDownLatch latch;

        public Racer(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                //所有的线程会一直等待
                this.latch.await();
                System.out.println(getName() + " start run" + System.currentTimeMillis());
            } catch (Exception e) {
                System.out.println("error..." + e.getMessage());
            }
        }
    }

    static class Worker extends Thread {
        private CountDownLatch latch;

        public Worker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((int) (Math.random() * 100));
                if (Math.random() < 0.02) {
                    throw new RuntimeException("good luck");
                }
            } catch (Exception e) {
                System.out.println("error" + e.getMessage());
            } finally {
                this.latch.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int num = 10;
        CountDownLatch latch = new CountDownLatch(1);
        Thread[] racers = new Thread[num];
        for (int i = 0; i < num; i++) {
            racers[i] = new Racer(latch);
            racers[i].start();
        }
        Thread.sleep(1000);
        latch.countDown();
    }

}
