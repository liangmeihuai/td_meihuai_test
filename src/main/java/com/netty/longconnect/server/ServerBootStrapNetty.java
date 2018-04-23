package com.netty.longconnect.server;

import com.netty.longconnect.Constants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.compression.SnappyFramedDecoder;
import io.netty.handler.codec.compression.SnappyFramedEncoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/21 22:21
 * @since 1.0
 */
public class ServerBootStrapNetty {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
                .option(ChannelOption.TCP_NODELAY, true)
                // 保持长连接状态
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        /*
                         * 使用 weakCachingConcurrentResolver 创建线程安全的 WeakReferenceMap
                         * ,对类加载器进行缓存
                         * ,它支持多线程并发访问,当虚拟机内存不足时,会释放缓存中的内存,防止内存泄露,为了房子异常码流和解码错位导致的内存溢出
                         * ,这里将当个对象序列化之后的字节数组长度设置为1M
                        */
                        ch.pipeline().addLast(new SnappyFramedDecoder());
                        ch.pipeline().addLast(new SnappyFramedEncoder());
                        ch.pipeline().addLast(new ObjectDecoder(1024 * 1024, ClassResolvers.weakCachingConcurrentResolver(null)));
                        ch.pipeline().addLast(new ObjectEncoder());
                        ch.pipeline().addLast(new MsgHandler());
                    }
                });
        ChannelFuture channelFuture = null;
        try {
            // 绑定端口,随后调用它的同步阻塞方法 sync 等等绑定操作成功,完成之后 Netty 会返回一个 ChannelFuture
            // 它的功能类似于的 Future,主要用于异步操作的通知回调.
            channelFuture = serverBootstrap.bind(Constants.PORT).sync();
            // 等待服务端监听端口关闭,调用 sync 方法进行阻塞,等待服务端链路关闭之后 main 函数才退出.
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }

    }
}
