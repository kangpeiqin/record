package com.proxy;

import com.kk.basic.proxy.jdk.myBatisMapper.AuthorMapper;
import com.kk.basic.proxy.jdk.myBatisMapper.BlogMapper;
import com.kk.basic.proxy.jdk.myBatisMapper.MapperRegistry;
import org.junit.Test;

/**
 * @author KPQ
 * @date 2021/10/12
 */
public class JDKProxyTest {

    @Test
    public void jdkProxyTest() {
        MapperRegistry registry = new MapperRegistry();
        //创建代理对象，执行公共的代理逻辑
        AuthorMapper authorMapper = registry.getMapper(AuthorMapper.class);
        System.out.println(authorMapper.selectAuthor());
        BlogMapper blogMapper = registry.getMapper(BlogMapper.class);
        System.out.println(blogMapper.selectBlog(1));
    }

}
