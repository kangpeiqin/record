package com.kk.basic.multiThread;

/**
 * @author KPQ
 * @date 2022/1/6
 */

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author hrabbit
 * 2018/07/16.
 */
public class AtomicBooleanTest implements Runnable {

    private static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        AtomicBooleanTest ast = new AtomicBooleanTest();
        Thread thread1 = new Thread(ast);
        Thread thread = new Thread(ast);
        thread1.start();
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("thread:" + Thread.currentThread().getName() + ";flag:" + flag.get());
        while (flag.compareAndSet(true, false)) {
            System.out.println(Thread.currentThread().getName() + "---flag更新为:---" + flag.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

