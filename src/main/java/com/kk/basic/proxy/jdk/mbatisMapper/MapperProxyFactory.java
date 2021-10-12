package com.kk.basic.proxy.jdk.mbatisMapper;

import java.lang.reflect.Proxy;

/**
 * 工厂类：统一创建Mapper代理对象
 *
 * @author KPQ
 * @date 2021/10/12
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance() {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInterface);
        //创建代理对象
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),
                new Class[]{mapperInterface}, mapperProxy);
    }


}
