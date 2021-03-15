package com.example.hello_netty_09;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author xian.wang
 * @since 下午3:40 2021/3/15
 */
public class CustomServer {

    private static final int MAX_FRAME_LENGTH = 1024* 1024;
    // CustomMsg中length这个属性的大小，我们这边是int型，所以是4
    private static final int LENGTH_FIELD_LENGTH = 4;
    // 指的是我们这边length字段的起始位置，因为前面有type和flag两个属性，且这两个属性都是byte,两个就是2字节,所以偏移量是2
    private static final int LENGTH_FIELD_OFFSET = 2;
    // 指的是length这个属性的值，假如我们的body长度是40，有时候，有些人喜欢将length写成44，因为length是int类型的，本身还占4个字节，
    // 这样就需要调整一下，那么就需要写-4，我们没有这样做，所以写0就可以了
    private static final int LENGTH_ADJUSTMENT = 0;
    private static final int INITIAL_BYTES_TO_STRIP = 0;

    private int port;

    public CustomServer(int port) {
        this.port = port;
    }

    public void start(){
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new CustomEncoder())
                                    .addLast(new CustomDecoder(MAX_FRAME_LENGTH, LENGTH_FIELD_LENGTH, LENGTH_FIELD_OFFSET,
                                    LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP, false))
                                    .addLast(new CustomServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println("Server start listen at " + port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new CustomServer(8080).start();
    }
}
