package com.kk.designPattern.Singleton;

/**
 * 确保一个类只有一个实例，并提供该实例的静态访问方法。
 * 私有构造函数保证了不能通过构造函数来创建对象实例，
 * 只能通过公有静态函数返回唯一的私有静态变量。
 * 1、 懒汉式-线程不安全
 * 私有静态实例变量被延迟实例化，这样做的好处是，
 * 如果没有用到该类，那么就不会实例化对象，从而节约资源。多线程环境下是不安全的
 *
 * @author kpq
 * @since 1.0.0
 */
public class SingletonLazy {

    private static SingletonLazy singleton;

    private SingletonLazy() {
    }

    public static SingletonLazy getInstance() {
        //多线程环境下可能同时进入，创建多个实例
        if (singleton == null) {
            singleton = new SingletonLazy();
        }
        return singleton;
    }

    /**
     * 线程安全：但是当一个线程进入该方法之后，
     * 其它试图进入该方法的线程都必须等待，即使 singleton 已经被实例化了
     */
    public static synchronized SingletonLazy getSingleton() {
        if (singleton == null) {
            singleton = new SingletonLazy();
        }
        return singleton;
    }

}
