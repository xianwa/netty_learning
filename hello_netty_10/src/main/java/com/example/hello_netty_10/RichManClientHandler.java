package com.example.hello_netty_10;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xian.wang
 * @since 下午1:54 2021/3/16
 */
public class RichManClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("=======================================");
        RichManProto.RichMan.Builder builder = RichManProto.RichMan.newBuilder();
        builder.setName("王思聪");
        builder.setId(1);
        builder.setEmail("wsc@163.com");

        List<RichManProto.RichMan.Car> cars = new ArrayList<>();
        RichManProto.RichMan.Car car1 = RichManProto.RichMan.Car.newBuilder().setName("上海大众超跑").setType(RichManProto.RichMan.CarType.DASAUTO).build();
        RichManProto.RichMan.Car car2 = RichManProto.RichMan.Car.newBuilder().setName("Aventador").setType(RichManProto.RichMan.CarType.DASAUTO).build();
        RichManProto.RichMan.Car car3 = RichManProto.RichMan.Car.newBuilder().setName("奔驰SLS级AMG").setType(RichManProto.RichMan.CarType.DASAUTO).build();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        builder.addAllCars(cars);
        ctx.writeAndFlush(builder.build());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
