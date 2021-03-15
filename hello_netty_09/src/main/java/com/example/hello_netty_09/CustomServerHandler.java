package com.example.hello_netty_09;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xian.wang
 * @since 下午3:54 2021/3/15
 */
public class CustomServerHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof CustomMsg){
            CustomMsg customMsg = (CustomMsg) msg;
            System.out.println("Client->Server:" + ctx.channel().remoteAddress() + " send " + customMsg.getBody());
//            String content = "hello,client,I am server";
            String content = "In this chapter you general, we recommend Java Concurrency in Practice by Brian Goetz. His book w" +
                    "ill give We’ve reached an exciting point—in the next chapter we’ll discuss bootstrapping, the process "
                    + "of configuring and connecting all of Netty’s components to bring your learned about threading models in ge"
                    + "neral and Netty’s threading model in particular, whose performance and consistency advantages we discuss"
                    + "ed in detail In this chapter you general, we recommend Java Concurrency in Practice by Brian Goetz. Hi"
                    + "s book will give We’ve reached an exciting point—in the next chapter we’ll discuss bootstrapping, the"
                    + " process of configuring and connecting all of Netty’s components to bring your learned about threading "
                    + "models in general and Netty’s threading model in particular, whose performance and consistency advantag"
                    + "es we discussed in detailIn this chapter you general, we recommend Java Concurrency in Practice by Bri"
                    + "an Goetz. His book will give We’ve reached an exciting point—in the next chapter;the counter is: 1 2222"
                    + "sdsa ddasd asdsadas dsadasdas";
            ctx.writeAndFlush(new CustomMsg((byte) 0xAB, (byte) 0xCD, content.length(),
                    content));
        }
    }
}
