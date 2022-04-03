package com.kk.basic.multiThread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author KPQ
 * @date 2022/1/24
 */
public class ParkTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            //放弃CPU
            LockSupport.park();
            System.out.println("exit");
        });
        t.start();
        Thread.sleep(10000);
        //调用unpack,线程t恢复运行
        LockSupport.unpark(t);
    }
}
