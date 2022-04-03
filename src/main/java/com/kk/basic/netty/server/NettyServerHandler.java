package com.kk.basic.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.util.Date;


/**
 * 服务器端业务处理逻辑
 *  1、刚建立连接时需要做什么处理
 *  2、收到消息后该怎么处理
 * @author KPQ
 * @date 2021/10/26
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    private static final String QUIT = "quit";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println("服务端接收的消息 : " + msg);
        //服务端断开连接的条件
        if (QUIT.equals(msg)) {
            ctx.close();
        }
        Date date = new Date();
        // 将消息返回给客户端
        ctx.writeAndFlush(date + "\n");
    }

    /**
     * 建立连接时，返回消息
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
        ctx.writeAndFlush("客户端" + InetAddress.getLocalHost().getHostName() + "成功与服务端建立连接！ \n");
        //将消息返回给客户端
        super.channelActive(ctx);
    }
}

