package com.kk.basic.multiThread;

/**
 * 线程间的通信
 * 等待/通知机制
 * 基于“锁”的方式，线程需要不断地去尝试获得锁，如果失败了，再继续尝试。这可能会耗费服务器资源。
 * 一个锁同一时刻只能被一个线程持有。而假如线程A现在持有了一个锁lock并开始执行，
 * 它可以使用lock.wait()让自己进入等待状态。这个时候，lock这个锁是被释放了的。
 * 等待/通知机制使用的是使用同一个对象锁，如果有两个线程使用的是不同的对象锁，
 * 那它们之间是不能用等待/通知机制通信的。
 *
 * sleep方法是Thread类的一个静态方法。它的作用是让当前线程睡眠一段时间。
 * sleep方法是不会释放当前的锁的，而wait方法会。
 * - wait可以指定时间，也可以不指定；而sleep必须指定时间。
 * - wait释放cpu资源，同时释放锁；sleep释放cpu资源，但是不释放锁，所以易死锁。
 * - wait必须放在同步块或同步方法中，而sleep可以再任意位置
 *
 * @author KPQ
 * @date 2021/10/27
 */
public class WaitAndNotify {
    private static final Object lock = new Object();

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("ThreadA: " + i);
                        //唤醒所有线程
                        lock.notify();
                        //等待，并释放锁
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("ThreadB: " + i);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        //等待1s
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }

}
