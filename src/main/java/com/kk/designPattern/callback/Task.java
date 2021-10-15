package com.kk.designPattern.callback;

import java.util.Optional;

/**
 * @author KPQ
 * @since 1.0
 */
public abstract class Task {

    /**
     * 传入回调逻辑
     */
    final void executeWith(Callback callback) {
        //执行任务
        execute();
        //判断是否有传入回调逻辑，如果有，则进行回调
        Optional.ofNullable(callback).ifPresent(Callback::call);
    }

    /**
     * 任务执行逻辑
     */
    public abstract void execute();

}
