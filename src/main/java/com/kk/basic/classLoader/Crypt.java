package com.kk.basic.classLoader;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 对指定文件进行加密
 *
 * @author KPQ
 * @date 2021/10/15
 */
public class Crypt {

    public static void crypt(int key, String source, String dest) {
        try (FileInputStream in = new FileInputStream(source)) {
            FileOutputStream out = new FileOutputStream(dest);
            int ch;
            while ((ch = in.read()) != -1) {
                byte c = (byte) (ch + key);
                out.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
