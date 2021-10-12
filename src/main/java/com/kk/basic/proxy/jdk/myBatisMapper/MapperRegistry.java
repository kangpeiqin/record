package com.kk.basic.proxy.jdk.myBatisMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper接口注册
 *
 * @author KPQ
 * @date 2021/10/12
 */
public class MapperRegistry {

    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();


    public MapperRegistry() {
        addMapper(AuthorMapper.class);
        addMapper(BlogMapper.class);
    }

    private <T> void addMapper(Class<T> type) {
        knownMappers.put(type, new MapperProxyFactory<>(type));
    }

    /**
     * 获取代理对象
     *
     * @param type
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> type) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        return mapperProxyFactory.newInstance();
    }

}