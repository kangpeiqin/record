package com.kk.basic.multiThread;

import com.kk.basic.unsafe.UnsafeTest;
import sun.misc.Unsafe;

import java.util.concurrent.*;

/**
 * @author KPQ
 * @date 2022/1/7
 */
public class Demo {
    private static final Unsafe unsafe = UnsafeTest.reflectGetUnsafe();
    private static final long headOffset;
    private static final long tailOffset;

    static {
        try {
            headOffset = unsafe.objectFieldOffset
                    (Demo.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                    (Demo.class.getDeclaredField("tail"));

        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private transient volatile Node head;

    private transient volatile Node tail;

    public static final class Node {
        int val;
        volatile Node prev;
        volatile Node next;

        Node() {

        }

        Node(int val) {
            this.val = val;
        }
    }

    private final boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    /**
     * CAS tail field. Used only by enq.
     */
    private final boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    private Node enq(final Node node) {
        for (; ; ) {
            Node t = tail;
            if (t == null) {
                if (compareAndSetHead(new Node())) {
                    tail = head;
                }
            } else {
                node.prev = t;
                if (compareAndSetTail(t, node)) {
                    t.next = node;
                    return t;
                }
            }
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        ExecutorService executor = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executor.execute(() -> demo.enq(new Node(index)));
        }
        Node node = demo.head;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
        executor.shutdown();
    }

}
