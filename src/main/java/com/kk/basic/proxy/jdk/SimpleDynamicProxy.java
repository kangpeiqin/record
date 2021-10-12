package com.kk.basic.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理：可以为一组实现了共同接口的目标类类创建代理类(有JVM反射进行创建)
 *  代理对象的创建方式变了，使用java.lang.reflect
 *  包中的Proxy类的静态方法newProxyInstance来创建代理对象
 *
 * InvocationHandler 对代理接口所有方法的调用都会转给该方法
 *
 * 可以编写通用的代理逻辑，用于各种类型的被代理对象，
 * 而不需要为每个被代理的类型都创建一个静态代理类。
 *
 * @author KPQ
 * @date 2021/10/12
 */
public class SimpleDynamicProxy {

    interface IService {
        void sayHello();
    }

    static class RealService implements IService {

        @Override
        public void sayHello() {
            System.out.println("hello");
        }
    }

    /**
     * 代理类逻辑
     */
    static class SimpleInvocationHandler implements InvocationHandler {

        private Object realObj;

        SimpleInvocationHandler(Object realObj) {
            this.realObj = realObj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("entering...");
            //实际对象的方法调用
            Object result = method.invoke(realObj, args);
            System.out.println("leaving...");
            return result;
        }
    }

    public static void main(String[] args) {
        IService realService = new RealService();
        //由 JVM 动态生成代理类
        IService proxyService = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(),
                new Class<?>[]{IService.class}, new SimpleInvocationHandler(realService));
        proxyService.sayHello();
    }

}
