package com.kk.basic.multiThread;

/**
 * 线程同步是线程之间按照一定的顺序执行
 * 想等A先执行完之后，再由B去执行
 * 同一时间只有一个线程持有一个锁，
 * 那么线程B就会等线程A执行完成后释放lock，线程B才能获得锁lock。
 *
 * @author KPQ
 * @date 2021/10/27
 */
public class ObjectLock {

    private static Object lock = new Object();

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("Thread A " + i);
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("Thread B " + i);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        //防止线程B先得到锁
        Thread.sleep(10);
        new Thread(new ThreadB()).start();
    }

}
