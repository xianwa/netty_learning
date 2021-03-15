https://blog.csdn.net/linuu/article/details/51338538
拆包:
  我们在我们巨长的req中末尾加了$$__
  在BaseServer的handler中添加DelimiterBasedFrameDecoder，它是继承ByteToMessageDecoder的，是将byte类型转化成Message的，所以我们应该将这个解码器
放在inbound处理器链的第一个
    .addLast(new DelimiterBasedFrameDecoder(2048, Unpooled.copiedBuffer("$$__".getBytes())))
  最后在client端发送了两次长文本，Server端也只接收了两次，正确。

  注意：
    2048规定的是一次内容最大支持的长度，假如说长文本内容超过了2048字节，则会报字段超长的错误