package com.kk.basic.multiThread;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 消费者队列
 *
 * @author KPQ
 * @date 2021/10/11
 */
public class MyBlockingQueue<E> {

    /**
     * 声明共享变量队列
     */
    private Queue<E> queue;

    private int limit;

    /**
     * 初始化对垒
     *
     * @param limit
     */
    public MyBlockingQueue(int limit) {
        this.limit = limit;
        queue = new ArrayDeque<>(limit);
    }

    /**
     * 往共享队列当中添加数据
     *
     * @param e
     * @throws InterruptedException
     */
    public synchronized void put(E e) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }
        queue.add(e);
        notifyAll();
    }

    /**
     * 往共享队列中获取数据
     *
     * @return
     * @throws InterruptedException
     */
    public synchronized E take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        E e = queue.poll();
        notifyAll();
        return e;
    }

}
