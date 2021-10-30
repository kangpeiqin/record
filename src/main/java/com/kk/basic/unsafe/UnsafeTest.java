package com.kk.basic.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author KPQ
 * @date 2021/10/22
 */
public class UnsafeTest {

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            return null;
        }
    }

}
