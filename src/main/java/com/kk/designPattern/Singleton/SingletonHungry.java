package com.kk.designPattern.Singleton;

/**
 * 饿汉式-线程安全
 * 丢失了延迟实例化带来的节约资源的好处
 *
 * @author kpq
 * @since 1.0.0
 */
public class SingletonHungry {

    private static SingletonHungry singleton = new SingletonHungry();

    private SingletonHungry() {
    }

    public static SingletonHungry getInstance() {
        return singleton;
    }

}
