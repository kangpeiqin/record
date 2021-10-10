package com.kk.basic.proxy.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * 回调过滤器CallbackFilter
 * 可以设置对不同方法执行不同的回调逻辑
 *
 * @author kpq
 * @since 1.0.0
 */
public class TargetMethodCallbackFilter implements CallbackFilter {

    private final String METHOD_NAME = "getParam";

    @Override
    public int accept(Method method) {

        if (method.getName().equals(METHOD_NAME)) {
            System.out.println("filter getParam == 1");
            return 1;
        } else {
            System.out.println("filter else == 0");
            return 0;
        }

    }

}
