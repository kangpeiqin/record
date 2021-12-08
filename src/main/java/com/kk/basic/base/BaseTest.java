package com.kk.basic.base;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 上界通配符<? extends T>:Java编译期只知道容器里面存放的是T和它的派生类，
 * 具体是什么类型不知道
 * 上界<? extends T>不能往里存，只能往外取，适合频繁往外面读取内容的场景
 * 下界<? super T>不影响往里存，但往外取只能放在Object对象里，适合经常往里面插入数据的场景
 */
public class BaseTest {

    static class Fruit {
    }

    static class Apple extends Fruit {
    }

    @Data
    @AllArgsConstructor
    static class Plate<T> {
        T item;
    }

    public static void main(String[] args) {
        //set()方法失效。但取东西get()方法还有效
        //Java编译期只知道容器里面存放的是Fruit和它的派生类，具体是什么类型不知道
        //可能是Fruit？可能是Apple？也可能是Banana，RedApple，GreenApple？
        Plate<? extends Fruit> p = new Plate<>(new Apple());
        //由于上界通配符设定容器中只能存放Fruit及其派生类，那么获取出来的我们都可以隐式的转为其基类（或者Object基类）
        // 所以上界描述符Extends适合频繁读取的场景
        Fruit fruit = p.getItem();
        Plate<? super Fruit> plate = new Plate<>(new Apple());
        plate.setItem(fruit);
    }

}
