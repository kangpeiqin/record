package com.kk.basic.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author KPQ
 * @date 2021/10/26
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 1.指定线程模型
                .group(workerGroup)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        //自定义业务 Handler
                        ch.pipeline().addLast(new NettyClientHandler());
                    }
                });
        // 4.建立连接
        ChannelFuture future = bootstrap
                .connect("127.0.0.1", 80)
                .addListener(f -> {
                    if (f.isSuccess()) {
                        System.out.println("连接成功...");
                    } else {
                        System.out.println("连接失败，开始重连");
                        bootstrap.connect("127.0.0.1", 80);
                    }
                })
                .sync();
    }
}


