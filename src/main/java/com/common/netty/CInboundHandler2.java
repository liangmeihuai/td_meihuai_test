package com.common.netty;

import com.specialtroops.chapter04.socket.Commons;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CInboundHandler2 extends ChannelInboundHandlerAdapter {
    private static Logger   logger  = LoggerFactory.getLogger(CInboundHandler2.class);
  
    @Override  
    // 读取Client发送的信息，并打印出来  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Commons.logInfo("InboundHandler2.channelRead: ctx :" + ctx);
        ByteBuf result = (ByteBuf) msg;  
        byte[] result1 = new byte[result.readableBytes()];  
        result.readBytes(result1);  
        String resultStr = new String(result1);  
        System.out.println("Client said:" + resultStr);  
        result.release();  
  
        ctx.write(msg);  
    }  
  
    @Override  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        logger.info("InboundHandler2.channelReadComplete");  
        ctx.flush();  
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Commons.logInfo("HelloClientIntHandler.channelActive");
        String msg = "Are you ok?";
        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
        encoded.writeBytes(msg.getBytes());
        ctx.write(encoded);
        ctx.flush();
    }
}