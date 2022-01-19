package com.kk.basic.multiThread;

/**
 * 如果开发者希望将类的某个静态变量（user ID或者transaction ID）与线程状态关联，则可以考虑使用ThreadLocal。
 * 最常见的ThreadLocal使用场景为用来解决数据库连接、Session管理等。数据库连接和Session管理涉及多个复杂对象的初始化和关闭。
 * <p>
 * ThreadLocal是一个本地线程副本变量工具类。内部是一个弱引用的Map来维护
 * ThreadLocal类并不属于多线程间的通信，而是让每个线程有自己”独立“的变量，线程之间互不影响。它为每个线程都创建一个副本，每个线程可以访问自己内部的副本变量。
 *
 * @author KPQ
 * @date 2021/10/27
 */
public class ThreadLocalDemo {

    static class ThreadA implements Runnable {
        private ThreadLocal<String> threadLocal;

        public ThreadA(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            threadLocal.set("A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA输出：" + threadLocal.get());
        }

        static class ThreadB implements Runnable {
            private ThreadLocal<String> threadLocal;

            private ThreadLocal<Integer> keyStore;

            public ThreadB(ThreadLocal<String> threadLocal) {
                this.threadLocal = threadLocal;
                this.keyStore = new ThreadLocal<>();
            }

            @Override
            public void run() {
                threadLocal.set("B");
                keyStore.set(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadB输出：" + threadLocal.get() + keyStore.get());
            }
        }

        public static void main(String[] args) {
            //两个不同的线程操作同一个 ThreadLocal 对象，实际上会
            // 转成对当前线程的 ThreadLocalMap对象进行操作
            ThreadLocal<String> threadLocal = new ThreadLocal<>();
            new Thread(new ThreadA(threadLocal)).start();
            new Thread(new ThreadB(threadLocal)).start();
        }
    }

}
