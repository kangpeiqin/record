package com.kk.basic.multiThread;

/**
 * 消费者：只能有一个条件等待队列，这是Java wait/notify机制的局限性
 * 每个对象都有一把锁和等待队列，一个线程在进入synchronized代码块时，
 * 会尝试获取锁，如果获取不到则会把当前线程加入等待队列中，
 * 除了用于锁的等待队列，每个对象还有另一个等待队列，表示条件队列
 * 调用wait就会把当前线程放到条件队列上并阻塞，表示当前线程执行不下去了，它需要等待一个条件
 *
 * @author KPQ
 * @date 2021/10/11
 */
public class Consumer implements Runnable {

    private MyBlockingQueue<String> queue;

    public Consumer(MyBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String task = queue.take();
                System.out.println("handle task:" + task);
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
