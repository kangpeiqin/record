package com.gc;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 1、跟踪垃圾回收
 * 之所以要手动执行 gc 垃圾回收，
 * 是因为 JVM 自动的执行垃圾回收是需要一定的条件的
 * VM Options 中配置参数：-XX:+PrintGC
 *                      -XX:+PrintGCDetails GC详细信息
 *                      -XX:+PrintHeapAtGC  对「堆空间」的日志打印
 *                      -XX:+PrintGCTimeStamps 会在每次 GC 发生时，额外输出 GC 发生的时间
 * 使用这些参数启动 Java 虚拟机后，只要遇到 GC，就会打印日志。
 *                之前使用的空间 ——> 回收之后使用的空间(总空间)
 * [GC (System.gc())  9113K->1504K(249344K), 0.0036904 secs]
 * [Full GC (System.gc())  1504K->1334K(249344K), 0.0095641 secs]
 *
 * PSYoungGen: 代表了「年轻代」的回收
 * ParOldGen：「老年代」
 * Metaspace：「元空间」
 *
 * 2、跟踪类的加载与卸载（需要加载各种支持 Java 运行的核心类）
 * -XX:+TraceClassLoading 跟踪类的加载
 *
 * 3、配置堆空间与栈空间
 * -Xms：设置堆的初始空间大小；
 * -Xmx：设置堆的最大空间大小。
 *
 * @author kpq
 * @since 1.0.0
 */
public class GCTest {


    @Test
    public void printGC() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        //为了看到GC的效果，进行手动GC
        System.gc();
    }

    @Test
    public void tracingClass(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
    }

}
