package com.kk.designPattern.Singleton;

/**
 * 双重校验锁-线程安全
 * Q&A: 采用 volatile 进行修饰的原因：
 * 因为cpu执行程序的时候，为了提高运算效率，所有的指令都是并发的乱序执行。
 * JVM 具有指令重排的特性，使用 volatile 可以禁止 JVM 的指令重排
 * 1、为 singleton 分配内存空间
 * 2、初始化 singleton
 * 3、将 singleton 指向分配的内存地址
 * 但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1 >3  > 2。
 * 指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例
 *
 * @author kpq
 * @since 1.0.0
 */
public class Singleton {

    private volatile static Singleton singleton;

    private Singleton() {
    }

    public static Singleton getSingleton() {
        //检测是否被实例化
        if (singleton == null) {
            //多线程情况下可能存在同时进入的情况，用 synchronized 保证同步顺序
            synchronized (Singleton.class) {
                //双重校验锁，只能有一个线程进入，防止创建多个对象
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
