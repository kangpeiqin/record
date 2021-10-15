package com.basic;

import org.junit.Test;

import java.security.*;
import java.util.Base64;

/**
 * RSA数字签名算法主要分为MD系列和SHA系列两大类。
 * MD系列主要包括MD2withRSA和MD5withRSA共2种数字签名算法；SHA系列主要包括SHA1withRSA、
 * SHA224withRSA、SHA256withRSA、SHA384withRSA和SHA512withRSA共5种数字签名算法
 *
 * @author KPQ
 * @since 1.0
 */
public class CryptTest {

    @Test
    public void cryptTest() throws Exception {
        String value = "value";
        // 非对称加密算法
        String algorithm = "RSA";
        String signAlgorithm = "SHA256withRSA";
        // ----- 公私钥生成 --------
        // 实例化秘钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 初始化，秘钥长度512~16384位，64倍数
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 公钥
        PublicKey publicKey = keyPair.getPublic();
        //用Base64打印二进制值
        System.out.println("RSA公钥: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        // 私钥
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("RSA私钥: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        // ----- 私钥加签 ---------
        // 获取签名对象
        Signature signature = Signature.getInstance(signAlgorithm);
        signature.initSign(privateKey);
        signature.update(value.getBytes());
        byte[] sign = signature.sign();
        System.out.println("签名值: " + Base64.getEncoder().encodeToString(sign));

        // ----- 公钥验签 ---------
        signature.initVerify(publicKey);
        signature.update(value.getBytes());
        System.out.println("验签结果: " + signature.verify(sign));
    }

}
