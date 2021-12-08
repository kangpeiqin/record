package com.kk.basic.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty 服务端配置
 * 服务器端的基本配置+业务逻辑处理实现
 * 流程：NettyClient --> NettyClientHandler(接收消息：channelRead,发送消息：channelActive)
 * --> NettyServer  ---> NettyServerHandler(channelRead:接收和发送消息)
 *
 * @author KPQ
 * @date 2021/10/26
 */
public class NettyServer {

    /**
     * 设置服务器监听端口
     */
    private static final int port = 6789;
    /**
     * 通过 nio 的方式来接收和处理连接，线程组：主要监听客户端的请求
     */
    private static EventLoopGroup group = new NioEventLoopGroup();

    /**
     * 线程组：主要处理具体的业务
     */
    private static NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    /**
     * 启动类
     */
    private static ServerBootstrap serverBootstrap = new ServerBootstrap();

    public static void main(String[] args) throws InterruptedException {
        try {
            serverBootstrap.group(group, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //设置过滤器
                    .childHandler(new NettyServerFilter());
            //服务器端绑定监听端口
            ChannelFuture f = serverBootstrap.bind(port).sync();
            System.out.println("服务端启动成功...");
            // 监听服务器关闭监听
            f.channel().closeFuture().sync();
        } finally {
            //关闭连接，释放所有资源
            group.shutdownGracefully();
        }
    }

}
