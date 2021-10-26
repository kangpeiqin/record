package com.kk.basic.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 流程：NettyClient --> NettyClientHandler(接收消息：channelRead,发送消息：channelActive)
 * --> NettyServer  ---> NettyServerHandler(channelRead:接收和发送消息)
 *
 * @author KPQ
 * @date 2021/10/26
 */
public class NettyServer {

    public static void main(String[] args) {
        //线程组：主要监听客户端的请求
        NioEventLoopGroup group = new NioEventLoopGroup();
        //线程组：主要处理具体的业务
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        //启动类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                //指定线程组
                .group(group, workerGroup)
                //指定NIO模式
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyServerHandler());
                    }
                });
        serverBootstrap.bind(80);
    }

}
