package com.kk.basic.proxy.cglib;

import lombok.Getter;

/**
 * 没有实现接口，需要CGlib动态代理的目标类
 * @author kpq
 * @since 1.0.0
 */
public class TargetObject {

    @Getter
    private Integer count;

    public String getParam(String paramName){
        return paramName;
    }
}
