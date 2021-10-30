package com.kk.designPattern.callback;


/**
 * @author KPQ
 * @since 1.0
 */
public final class SimpleTask extends Task {

    @Override
    public void execute() {
        System.out.println("--发送消息---");
    }

    public static void main(String[] args) {
        Task task = new SimpleTask();
        task.executeWith(() -> System.out.println("---消息发送成功----"));
    }
}
