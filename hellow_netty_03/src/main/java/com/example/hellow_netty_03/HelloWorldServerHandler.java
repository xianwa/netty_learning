package com.example.hellow_netty_03;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xian.wang
 * @since 下午8:16 2021/3/13
 */
public class HelloWorldServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server channel read");
        System.out.println(ctx.channel().remoteAddress() + "->Server :" + msg.toString());
        ctx.writeAndFlush("server write" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
