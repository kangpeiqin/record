package com.kk.basic.proxy.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * 在CGLib回调时可以设置对不同方法执行不同的回调逻辑，或者根本不执行回调。
 *
 * @author kpq
 * @since 1.0.0
 */
public class TargetMethodCallbackFilter implements CallbackFilter {

    private final String METHOD_NAME = "getParam";

    @Override
    public int accept(Method method) {

        if (method.getName().equals(METHOD_NAME)) {
            System.out.println("filter getParam == 0");
            return 1;
        } else {
            return 0;
        }

    }

}
