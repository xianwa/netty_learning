package com.example.hello_netty_11;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xian.wang
 * @since 下午2:51 2021/3/16
 */
public class HeartServerHandle extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("server receive msg:" + msg);
    }
}
