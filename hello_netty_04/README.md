https://blog.csdn.net/linuu/article/details/51315373
相比之前进行的改动：
在HelloWorldClient中添加了 .addLast(new BaseClient1Handler()).addLast(new BaseClient2Handler())
最后执行结果：只打印了BaseClient1Handler channelRead0 received:server writeHello Netty Server,I am a common client
也就是说，多个handler，只执行了第一个

当在BaseHandler中添加了 channelHandlerContext.fireChannelRead(msg) 之后，执行结果变成了：
BaseClient1Handler channelRead0 received:server writeHello Netty Server,I am a common client
BaseClient2Handler channelRead0 received:server writeHello Netty Server,I am a common client

也就是说，只有使用了fire**方法，handler才会继续向下传递