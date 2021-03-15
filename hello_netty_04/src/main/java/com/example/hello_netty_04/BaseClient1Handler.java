package com.example.hello_netty_04;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xian.wang
 * @since 上午11:22 2021/3/15
 */
public class BaseClient1Handler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("BaseClient1Handler channelRead0 received:" + msg);
        channelHandlerContext.fireChannelRead(msg);
    }
}
