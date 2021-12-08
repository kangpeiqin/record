package com.kk.basic.classLoader;

import java.lang.reflect.Method;

/**
 * 类加载测试
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        int key = 1;
        String userDir = System.getProperty("user.dir");
        String source = userDir + "\\target\\classes\\com\\kk\\base\\classLoader\\Message.class";
        String dist = userDir + "\\src\\main\\java\\com\\kk\\base\\classLoader\\Message.crypt";
        Crypt.crypt(key, source, dist);
        try {
            ClassLoader loader = new CryptoClassLoader(key, dist);
            //加载类
            Class<?> c = loader.loadClass("com.kk.basic.classLoader.Message");
            //执行方法
            Message message = (Message) c.newInstance();
            message.print();
            Method m = c.getMethod("main", String[].class);
            m.invoke(null, (Object) new String[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
