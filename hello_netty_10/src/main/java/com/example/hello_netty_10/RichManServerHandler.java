package com.example.hello_netty_10;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xian.wang
 * @since 上午10:36 2021/3/16
 */
public class RichManServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RichManProto.RichMan req = (RichManProto.RichMan) msg;
        System.out.println(req.getName() + "他有" + req.getCarsCount() + "量车");
        List<RichManProto.RichMan.Car> lists = req.getCarsList();
        if (lists != null) {
            for (RichManProto.RichMan.Car car : lists) {
                System.out.println(car.getName());
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
