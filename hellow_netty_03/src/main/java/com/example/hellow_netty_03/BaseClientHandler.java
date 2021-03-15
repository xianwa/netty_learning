package com.example.hellow_netty_03;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xian.wang
 * @since 上午11:22 2021/3/15
 */
public class BaseClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("Client channelRead0 received:" + msg);
    }
}
