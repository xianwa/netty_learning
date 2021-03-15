package com.example.hello_netty_06.pack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author xian.wang
 * @since 上午11:51 2021/3/15
 */
public class BaseClient {

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new StringDecoder())
                                    .addLast(new BaseClientHandler());
                        }
                    });
            ChannelFuture future = b.connect("127.0.0.1", 8080).sync();
            future.channel().writeAndFlush("Hello Netty Server,I am a common client");
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            group.shutdownGracefully();
        }

    }


}
