package com.common.netty;

import com.specialtroops.chapter04.socket.Commons;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CInboundHandler1 extends ChannelInboundHandlerAdapter {
    private static Logger   logger  = LoggerFactory.getLogger(CInboundHandler1.class);
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Commons.logInfo("InboundHandler1.channelRead: ctx :" + ctx);
        // 通知执行下一个InboundHandler  
        ctx.fireChannelRead(msg);  
    }  
  
    @Override  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        logger.info("InboundHandler1.channelReadComplete");  
        ctx.flush();  
    }  
}  