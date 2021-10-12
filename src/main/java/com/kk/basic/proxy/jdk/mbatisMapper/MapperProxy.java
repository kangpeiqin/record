package com.kk.basic.proxy.jdk.mbatisMapper;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * MyBatis 动态代理的使用简单示例
 * 统一的执行逻辑
 * 例如：
 * 1、BlogMapper.java
 * public interface BlogMapper {
 * Blog selectBlog(int id);
 * }
 * 2、AuthorMapper.java
 * public interface AuthorMapper {
 * List<AuthorMapper> selectAllAuthors();
 * }
 * 接口--代理对象--执行代理对象方法逻辑
 *
 * @author KPQ
 * @date 2021/10/12
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final Class<T> mapperInterface;

    public MapperProxy(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //调用从Object类继承的方法不做处理
        if (Object.class.equals(method.getDeclaredAnnotations())) {
            return method.invoke(this, args);
        } else {
            return new MapperMethod(method.getName()).execute();
        }
    }

}
