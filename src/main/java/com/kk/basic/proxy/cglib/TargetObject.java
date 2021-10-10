package com.kk.basic.proxy.cglib;


/**
 * 目标对象，被代理对象
 * 没有实现接口，需要CGlib动态代理的目标类
 *
 * @author kpq
 * @since 1.0.0
 */
public class TargetObject {

    public String getParam(String paramName) {
        return paramName;
    }

    public Integer getCount(Integer count) {
        return count;
    }
}
