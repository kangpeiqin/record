package com.basic.classLoader;

import com.kk.designPattern.callback.SimpleTask;
import org.junit.Test;

/**
 * @author KPQ
 * @date 2021/10/15
 */
public class LoaderTest {

    @Test
    public void loadClass() {
        try {
            Class<?> clazz = Class.forName("com.kk.designPattern.callback.SimpleTask");
            SimpleTask task = (SimpleTask) clazz.newInstance();
            task.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
