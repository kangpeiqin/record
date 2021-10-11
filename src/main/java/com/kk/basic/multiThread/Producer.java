package com.kk.basic.multiThread;


/**
 * 生产者
 *
 * @author KPQ
 * @date 2021/10/11
 */
public class Producer implements Runnable {

    private MyBlockingQueue<String> blockingDeque;

    public Producer(MyBlockingQueue<String> blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (true) {
                String task = String.valueOf(num);
                blockingDeque.put(task);
                System.out.println("produce task:" + task);
                num++;
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
        new Thread(new Consumer(queue)).start();
        new Thread(new Producer(queue)).start();
    }

}
