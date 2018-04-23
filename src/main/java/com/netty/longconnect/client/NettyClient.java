package com.netty.longconnect.client;

import com.netty.longconnect.Constants;
import com.netty.longconnect.LoginMsg;
import com.netty.longconnect.PingMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.compression.SnappyFramedDecoder;
import io.netty.handler.codec.compression.SnappyFramedEncoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/23 13:44
 * @since 1.0
 */
public class NettyClient {
    private static final int SEND_COUNT = 1_000_000;
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
//                .option(ChannelOption.RCVBUF_ALLOCATOR, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new SnappyFramedDecoder());
                        ch.pipeline().addLast(new SnappyFramedEncoder());
                        ch.pipeline().addLast(new ObjectDecoder(1 << 30L, ClassResolvers.weakCachingConcurrentResolver(null)));
                        ch.pipeline().addLast(new ObjectEncoder());
                        ch.pipeline().addLast(new NettyClientHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", Constants.PORT).sync();
            if (channelFuture.isSuccess()) {
                SocketChannel socketChannel = (SocketChannel) channelFuture.channel();
                System.out.println("success");
                LoginMsg loginMsg = new LoginMsg("loginMsg", "luke", "123456", "login-clientid-1");
                socketChannel.writeAndFlush(loginMsg);
                String pingClientId = "ping-clientid-1" + UUID.randomUUID().toString();
//                String pingClientId = "ping-clientid-1";
//                PingMsg pingMsg = new PingMsg("pingMsg", pingClientId);
//                socketChannel.writeAndFlush(pingMsg);
                int index = 0;
                while (true){
                    index ++;
//                    System.out.println("channelFuture.... channel---future" + channelFuture);
                    PingMsg pingMsg = new PingMsg("pingMsg", pingClientId);
                    pingMsg.setBaseMsg(pingMsg.getBaseMsg() + "_____" + index);
                    socketChannel.writeAndFlush(pingMsg);
                    if (index == SEND_COUNT){
                        System.out.println("wait.............................................");
                        TimeUnit.MILLISECONDS.sleep(20000); // 如果不加睡眠是因为客户端处理不过来吗，发送太频繁
                    }

                }
            }
            System.out.println("okay...done.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
