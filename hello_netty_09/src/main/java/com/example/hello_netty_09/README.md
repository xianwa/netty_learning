https://blog.csdn.net/linuu/article/details/51371595#commentBox
一起学Netty（九）之LengthFieldBasedFrameDecoder

decode和encode时的大小设置：
//判断传送客户端传送过来的数据是否按照协议传输，头部信息的大小应该是 byte+byte+int = 1+1+4 = 6
    private static final int HEADER_SIZE = 6;
    private byte type;
    private byte flag;
    private int length;
    private String body;

LengthFieldBasedFrameDecoder的几个相关参数
private static final int MAX_FRAME_LENGTH = 1024* 1024;
    // CustomMsg中length这个属性的大小，我们这边是int型，所以是4
    private static final int LENGTH_FIELD_LENGTH = 4;
    // 指的是我们这边length字段的起始位置，因为前面有type和flag两个属性，且这两个属性都是byte,两个就是2字节,所以偏移量是2
    private static final int LENGTH_FIELD_OFFSET = 2;
    // 指的是length这个属性的值，假如我们的body长度是40，有时候，有些人喜欢将length写成44，因为length是int类型的，本身还占4个字节，
    // 这样就需要调整一下，那么就需要写-4，我们没有这样做，所以写0就可以了
    private static final int LENGTH_ADJUSTMENT = 0;
    private static final int INITIAL_BYTES_TO_STRIP = 0;

网上说继承LengthFieldBasedFrameDecoder的方法不太合适，说是用组合的方式更适合
最后参考:https://www.jianshu.com/p/c90ec659397c  在CustomDecode的decode方法最前面添加这两行代码：
//在这里调用父类的方法,实现指得到想要的部分,我在这里全部都要,也可以只要body部分
        in = (ByteBuf) super.decode(ctx,in);
然后成功用这种方法进行粘包



