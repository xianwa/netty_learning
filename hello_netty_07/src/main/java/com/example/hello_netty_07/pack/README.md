https://blog.csdn.net/linuu/article/details/51338538
粘包:
server端添加 .addLast(new FixedLengthFrameDecoder(23))
    这个意思是获取的帧数据每有23个字节就切分一次
最后Server端接收了100次，正确解决了粘包的问题
