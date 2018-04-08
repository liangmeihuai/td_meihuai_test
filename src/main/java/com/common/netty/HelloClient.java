package com.common.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;  
import io.netty.channel.ChannelInitializer;  
import io.netty.channel.ChannelOption;  
import io.netty.channel.EventLoopGroup;  
import io.netty.channel.nio.NioEventLoopGroup;  
import io.netty.channel.socket.SocketChannel;  
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

/**
 * 在使用Handler的过程中，需要注意：

 1、ChannelInboundHandler之间的传递，通过调用 ctx.fireChannelRead(msg) 实现；调用ctx.write(msg) 将传递到ChannelOutboundHandler。

 2、ctx.write()方法执行后，需要调用flush()方法才能令它立即执行。

 3、ChannelOutboundHandler 在注册的时候需要放在最后一个ChannelInboundHandler之前，否则将无法传递到ChannelOutboundHandler。
 */
public class HelloClient {  
    public void connect(String host, int port) throws Exception {  
        EventLoopGroup workerGroup = new NioEventLoopGroup();  
  
        try {  
            Bootstrap b = new Bootstrap();  
            b.group(workerGroup);  
            b.channel(NioSocketChannel.class);  
            b.option(ChannelOption.SO_KEEPALIVE, true);  
            b.handler(new ChannelInitializer<SocketChannel>() {  
                @Override  
                public void initChannel(SocketChannel ch) throws Exception {  

//                    ch.pipeline().addLast(new COutboundHandler1());
//                    ch.pipeline().addLast(new COutboundHandler2());
//                    ch.pipeline().addLast(new CInboundHandler1());
//                    ch.pipeline().addLast(new CInboundHandler2());
                    ch.pipeline().addLast(new HelloClientIntHandler());
                }
            });  
  
            // Start the client.  
            ChannelFuture f = b.connect(host, port).sync();  
            f.channel().closeFuture().sync();  
        } finally {  
            workerGroup.shutdownGracefully();  
        }  
    }  
  
    public static void main(String[] args) throws Exception {  
        HelloClient client = new HelloClient();  
        client.connect("127.0.0.1", 8000);  
    }  
}  
