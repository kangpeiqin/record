package com.kk.basic.jdkCodeExplain;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BlockingQueue一般用于生产者-消费者模式，生产者是往队列里添加元素的线程，
 * 消费者是从队列里拿元素的线程。BlockingQueue就是存放元素的容器。
 */
public class ArrayBlockingQueue<E> {

    /**
     * 用数组存储元素，成员变量：每个对象都会持有一个
     */
    final Object[] items;

    int takeIndex;

    int putIndex;

    int count;

    /**
     * 可重入锁
     */
    final ReentrantLock lock;

    private final Condition notEmpty;

    private final Condition notFull;

    public ArrayBlockingQueue(int capacity, boolean fair) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        //初始化数组
        this.items = new Object[capacity];
        //初始化值，可重入锁
        lock = new ReentrantLock(fair);
        //显示锁当中运用显示条件
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void put(E e) throws InterruptedException {
        Objects.requireNonNull(e);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == items.length)
                notFull.await();
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }

    private void enqueue(E x) {
        // assert lock.getHoldCount() == 1;
        // assert items[putIndex] == null;
        final Object[] items = this.items;
        items[putIndex] = x;
        if (++putIndex == items.length)
            putIndex = 0;
        count++;
        //唤醒
        notEmpty.signal();
    }


}
