package com.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world Server.
 * 　当要添加多个handler时，就必须注意添加的顺序。

 　　这里的handler分为两种类型：

 　　　　一种继承ChannelInboundHandler，用于处理来自客户端的消息，比如对客户端的消息进行解码，读取等等。该类型在pipeline中的执行顺序与添加顺序一致。

 　　　　一种继承ChannelOutboundHandler，用于处理即将发往客户端的消息，比如对该消息进行编辑，编码等等。该类型在pipeline中的执行顺序与添加顺序相反。

 　　而且ChannelOutboundHandler的所有handler，放在ChannelInboundHandler下面是执行不到的。

 比如：

 复制代码
 public class childChannelHandler extends ChannelInitializer<SocketChannel>{
@Override
public void initChannel(SocketChannel ch) throws Exception {
ch.pipeline().addLast(new OutboundHandler1());　　//handler1
ch.pipeline().addLast(new OutboundHandler2());　　//handler2
ch.pipeline().addLast(new InboundHandler1());　　 //handler3
ch.pipeline().addLast(new InboundHandler2());　　 //handler4
}
}
 复制代码
 　　以上4个handler的实际执行顺序分别为handler3 -> handler4 -> handler2 ->handler1

 　　如果在handler4下方加上OutboundHandler3，那么这个handler是不会被执行到的。
 *
 * @author Ricky
 */
public class EchoServerTwo {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private int port;

    public EchoServerTwo(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)//设置channel类型
                     /**
                      *  2.5、此处可处理一个问题：超长字符串在服务端handler无法被一次接收完
                       可通过此句进行设置：.childOption(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator(64, MAX_LENGTH_OF_MSG, 65536))
                       * Handles a server-side channel.
                      */
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)//选择执行handler
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new EchoServerHandler());
                        }
                    });

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)
            System.out.println("server bind port: two " + port);
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServerTwo(Constants.PORT).run();
    }
}