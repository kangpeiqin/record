package com.kk.basic.multiThread;

/**
 * volatile关键字能够保证内存的可见性，如果用volatile关键字声明了一个变量，
 * 在一个线程里面改变了这个变量的值，那其它线程是立马可见更改后的值的。
 *
 * signal++并不是一个原子操作，所以我们在实际开发中，
 * 会根据需要使用synchronized给它“上锁”,或者是使用AtomicInteger等原子类
 *
 * @author KPQ
 * @date 2021/10/27
 */
public class Signal {

    private static volatile int signal = 0;

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 0) {
                    System.out.println("threadA: " + signal);
                    signal++;
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 1) {
                    System.out.println("threadB: " + signal);
                    signal = signal + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }

}
