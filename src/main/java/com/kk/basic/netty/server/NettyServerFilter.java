package com.kk.basic.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 服务器端过滤器
 *
 * @author KPQ
 * @date 2021/12/8
 */
public class NettyServerFilter extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline ph = ch.pipeline();
        //以("\n")为结尾分割的 解码器
        ph.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
                //添加编码器和解码器，应和客户端一致
                .addLast("decoder", new StringDecoder())
                .addLast("encoder", new StringEncoder())
                //服务器端业务逻辑
                .addLast("handler", new NettyServerHandler());
    }
}
