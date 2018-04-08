package com.common.netty;

import com.specialtroops.chapter04.socket.Commons;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class COutboundHandler2 extends ChannelOutboundHandlerAdapter {
    private static Logger   logger  = LoggerFactory.getLogger(COutboundHandler2.class);
      
    @Override  
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        Commons.logInfo("OutboundHandler2.write");
        // 执行下一个OutboundHandler  
        super.write(ctx, msg, promise);  
    }

}  