package com.proxy;

import com.kk.basic.proxy.cglib.TargetInterceptor;
import com.kk.basic.proxy.cglib.TargetObject;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * @author kpq
 * @since 1.0.0
 */
public class CglibTest {

    @Test
    public void cglibTest() {
        //Enhancer类是CGLib中的一个字节码增强器
        Enhancer enhancer = new Enhancer();
        //将被代理类TargetObject设置成父类
        enhancer.setSuperclass(TargetObject.class);
        //设置拦截器TargetInterceptor
        enhancer.setCallback(new TargetInterceptor());
        //执行enhancer.create()动态生成一个代理类,并从Object强制转型成父类型TargetObject
        TargetObject targetObject2 = (TargetObject) enhancer.create();
        System.out.println(targetObject2);
        //在代理类上调用方法
        System.out.println(targetObject2.getParam("param"));
    }

}
