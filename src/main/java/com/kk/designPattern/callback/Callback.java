package com.kk.designPattern.callback;

/**
 * Kafka 发送消息成功也提供了类似的回调
 * 发送成功--->调用自定义的回调逻辑
 * 任务执行完 ---> 在特定的时间回调 ---> 通知我们任务执行完成
 *
 * @author KPQ
 * @since 1.0
 */
public interface Callback {

    /**
     * 回调方法逻辑
     */
    void call();

}
