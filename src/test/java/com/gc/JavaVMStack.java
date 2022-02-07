package com.gc;

/**
 * @author KPQ
 * @date 2022/1/28
 */
public class JavaVMStack {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStack stack = new JavaVMStack();
        try {
            stack.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + stack.stackLength);
            throw  e;
        }
    }

}
