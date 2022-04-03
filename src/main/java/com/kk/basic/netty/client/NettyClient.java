package com.kk.basic.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;

/**
 * 客户端的主要工作：
 * 1、连接到服务端
 * 2、向服务端发送数据
 * 3、处理服务端返回的数据
 * 4、关闭连接
 *
 * @author KPQ
 * @date 2021/10/26
 */
public class NettyClient {

    /**
     * IP 地址
     */
    private static String host = "127.0.0.1";
    /**
     * 服务器端口号
     */
    private static int port = 6789;

    /**
     * 通过 nio 方式来接收和处理连接
     */
    private static EventLoopGroup group = new NioEventLoopGroup();

    private static Bootstrap bootstrap = new Bootstrap();

    private static Channel ch;

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("客户端成功启动...");

        // 1.指定线程模型
        bootstrap.group(group)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 3.IO 处理逻辑
                .handler(new NettyClientFilter());
        // 4.建立连接
        // 连接服务端
        ch = bootstrap.connect(host, port).sync().channel();
        //客户端发送数据
        star();
    }

    public static void star() throws IOException {
        String str = "Hello Netty";
        ch.writeAndFlush(str + "\r\n");
        System.out.println("客户端发送数据:" + str);
    }
}


