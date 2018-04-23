package com.netty.longconnect.client;

import com.netty.longconnect.BaseMsg;
import com.netty.longconnect.MsgType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/23 13:49
 * @since 1.0
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<BaseMsg> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("netty client handler is active.started.");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("netty client handler -client caught a exception is " + ExceptionUtils.getStackTrace(cause));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("Netty client handler is inactive.");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMsg msg) throws Exception {
        MsgType msgType = msg.getType();
        System.out.println("receive the msg is " + msg.getBaseMsg());
        switch (msgType) {
            case PING:
//                ctx.channel().writeAndFlush(msg);
                System.out.println("client ping===type..." + msg.getBaseMsg());
                break;
            case LOGIN:
//                ctx.channel().writeAndFlush(msg);
                System.out.println("client login===type...." + msg.getBaseMsg());
                break;
            default:
                throw new IllegalArgumentException("cannot parse the ( " + msgType + " ) type");
        }
        ReferenceCountUtil.release(msg);
    }
}
