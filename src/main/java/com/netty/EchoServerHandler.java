package com.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1、创建线程池

 创建处理连接的线程池:bossGroup
 创建处理所有事件的线程池:workerGroup

 EventLoopGroup bossGroup = new NioEventLoopGroup();
 EventLoopGroup workerGroup = new NioEventLoopGroup();


 2、设定辅助启动类。ServerBootStrap
 传入1中开辟的线程池
 指定连接该服务器的channel类型
 指定需要执行的childHandler
 设置部分参数，如AdaptiveRecvByteBufAllocator缓存大小
 .Option用于设置bossGroup相关参数
 .childOption用于设置workerGroup相关参数


 2.5、此处可处理一个问题：超长字符串在服务端handler无法被一次接收完
 可通过此句进行设置：.childOption(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator(64, MAX_LENGTH_OF_MSG, 65536))
 * Handles a server-side channel.
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter { // (1)
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        System.out.println("server channel read...");
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        try {
            String body = new String(req, "UTF-8");
            System.out.println("server channel read msg:" + body);
        }catch (Exception e){
            e.printStackTrace();
        }

        String response = "hello from server";
        ByteBuf resp = Unpooled.copiedBuffer(response.getBytes());
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channel read complete");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        logger.error("server caught exception", cause);
        ctx.close();
    }
}