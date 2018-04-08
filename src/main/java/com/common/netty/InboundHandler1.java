package com.common.netty;

import com.specialtroops.chapter04.socket.Commons;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InboundHandler1 extends ChannelInboundHandlerAdapter {  
    private static Logger   logger  = LoggerFactory.getLogger(InboundHandler1.class);  
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Commons.logInfo("InboundHandler1.channelRead: ctx :" + ctx);
        // 通知执行下一个InboundHandler
        // start
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        String resultStr = new String(result1);
        System.out.println("resultStr-IN-1 = " + resultStr);

        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBytes(result1);
        msg = byteBuf;

        // end
        ctx.fireChannelRead(msg);  
    }  
  
    @Override  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        logger.info("InboundHandler1.channelReadComplete");  
        ctx.flush();  
    }  
}  