package com.proxy;

import com.kk.basic.proxy.cglib.TargetInterceptor;
import com.kk.basic.proxy.cglib.TargetMethodCallbackFilter;
import com.kk.basic.proxy.cglib.TargetObject;
import com.kk.basic.proxy.cglib.TargetResultFixed;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
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
        TargetObject targetObject = (TargetObject) enhancer.create();
        //在代理类上调用方法
        targetObject.getParam("param");
        targetObject.getCount(4);
    }

    @Test
    public void callbackTest() {
        //字节码增强器
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        //回调过滤器
        CallbackFilter callbackFilter = new TargetMethodCallbackFilter();
        //NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截
        Callback noopCb = NoOp.INSTANCE;
        //方法拦截器
        Callback callback = new TargetInterceptor();
        //回调方法返回固定值
        Callback fixedValue = new TargetResultFixed();
        Callback[] cbArray = new Callback[]{callback, noopCb, fixedValue};
        enhancer.setCallbacks(cbArray);
        enhancer.setCallbackFilter(callbackFilter);
        TargetObject targetObject = (TargetObject) enhancer.create();
        System.out.println(targetObject);
        targetObject.getParam("param");
        targetObject.getCount(1);
    }

}
