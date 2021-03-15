package com.example.hello_netty_09;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author xian.wang
 * @since 下午3:23 2021/3/15
 */
public class CustomDecoder extends LengthFieldBasedFrameDecoder {

    // 判断传送客户端传送过来的数据是否按照协议传输，头部信息的大小应该是byte+byte+int -> 1 + 1 + 4 = 6
    private static final int HEADER_SIZE = 6;
    // 类型 系统编号 0xAB:表示A系统,0xBC:表示B系统
    private byte type;
    // 信息标志 0xAB:表示心跳包,0xBC:表示超时包,0xCD:业务信息包
    private byte flag;
    // 主题信息的长度
    private int length;
    // 主题信息
    private String body;

    public CustomDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        //在这里调用父类的方法,实现指得到想要的部分,我在这里全部都要,也可以只要body部分
        // 这段代码很重要
        in = (ByteBuf) super.decode(ctx,in);

        if (in == null) {
            return null;
        }
        if (in.readableBytes() < HEADER_SIZE) {
            throw new Exception("可读信息比头部信息都小");
        }
        // 注意在读的过程中,readIndex的指针也在移动
        type = in.readByte();
        flag = in.readByte();
        length = in.readInt();

        if (in.readableBytes() < length) {
            throw new Exception("可读信息长度比body字段的长度小");
        }

        ByteBuf buf = in.readBytes(length);
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        body = new String(req, "UTF-8");

        CustomMsg customMsg = new CustomMsg(type, flag, length, body);
        return customMsg;
    }
}
