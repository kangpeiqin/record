package com.kk.basic.classLoader;

import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 自定义类加载器
 * 继承 ClassLoader 类，重写 findClass 方法
 * - Java运行时，会根据类的完全限定名寻找并加载类
 * 加载流程：
 * 1、 判断是否已经加载过了，加载过了，直接返回Class对象，一个类只会被一个Class-Loader加载一次。
 * 2、如果没有被加载，先让父ClassLoader去加载，如果加载成功，返回得到的Class对象
 * 3、在父ClassLoader没有加载成功的前提下，自己尝试加载类。
 *
 * @author KPQ
 * @since 1.0
 */
@AllArgsConstructor
public class CryptoClassLoader extends ClassLoader {

    /**
     * 密钥
     */
    private int key;

    /**
     * 字节码路径
     */
    private String classLocation;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            //获取字节码字节数组
            byte[] classBytes = loadClassByBytes();
            //Converts an array of bytes into an instance of class,将字节数组转换成类实例
            Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
            if (cl == null) {
                throw new ClassNotFoundException(name);
            }
            return cl;
        } catch (Exception e) {
            throw new ClassNotFoundException(name);
        }
    }

    private byte[] loadClassByBytes() throws IOException {
        //从指定路径加载文件
        byte[] bytes = Files.readAllBytes(Paths.get(classLocation));
        //进行解密
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] - key);
        }
        //返回字节数据
        return bytes;
    }
}
