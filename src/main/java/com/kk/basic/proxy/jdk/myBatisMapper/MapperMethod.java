package com.kk.basic.proxy.jdk.myBatisMapper;

/**
 * Mapper中的方法类
 *
 * @author KPQ
 * @date 2021/10/12
 */
public class MapperMethod {

    private final String methodName;


    public MapperMethod(String methodName) {
        this.methodName = methodName;
    }

    public Object execute() {
        //代理对象中的方法被执行
        return methodName;
    }

}
