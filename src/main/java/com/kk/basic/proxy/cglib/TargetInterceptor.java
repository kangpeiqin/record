package com.kk.basic.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 定义一个拦截器。在调用目标方法时，CGLib会回调MethodInterceptor接口方法拦截
 *
 * @author kpq
 * @since 1.0.0
 */
public class TargetInterceptor implements MethodInterceptor {

    /**
     * 重写方法拦截在方法前和方法后加入业务
     *
     * @param o           目标对象 由CGLib动态生成的代理类实例
     * @param method      为目标方法 调用的被代理的方法引用
     * @param params      为参数，
     * @param methodProxy 方法代理对象 生成的代理类对方法的代理引用。
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        System.out.println("---------调用前------------");
        Object result = methodProxy.invokeSuper(o, params);
        System.out.println("执行结果：" + result);
        System.out.println("--------调用后------------");
        return result;
    }

}
