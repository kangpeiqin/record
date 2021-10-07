package com.designPattern;

import org.junit.Test;

/**
 * 指令重排序
 * 如果cpu不支持指令重排，那么就不会出现问题，
 * 比如在X86的CPU上运行代码测试，可能不会出现多个值
 *
 * @author kpq
 * @since 1.0.0
 */
public class HappenBeforeTest {

    class Demo {
        private int count = 2;
        private volatile boolean flag = false;

        public void setCount() {
            count = 10;
            //没有volatile修饰，实际执行顺序，有可能是flag=true先执行
            flag = true;
        }

        public void getCount() {
            if (flag) {
                System.out.println(count);
            }
        }
    }

    @Test
    public void happenBeforeTest() {
        for (int i = 0; i < 300; i++) {
            Demo demo = new Demo();
            Thread t1 = new Thread(demo::setCount);
            Thread t2 = new Thread(demo::getCount);
            t1.start();
            t2.start();
        }
    }

}
