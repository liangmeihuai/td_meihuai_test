package com.netty.longconnect.server;

import com.netty.longconnect.BaseMsg;
import com.netty.longconnect.LoginMsg;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/22 2:03
 * @since 1.0
 */
public class MsgHandler extends SimpleChannelInboundHandler<BaseMsg>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMsg msg) throws Exception {
        Channel channel = null;
        channel = ctx.channel();
        System.out.println(channel);
        switch (msg.getType()){
            case PING:
                System.out.println("receive the ping msg.........1 " + msg.getBaseMsg());// 收到客户端的ping消息需要发给客户端
                channel = ChannelMap.getChannelByClientId(msg.getType(), msg.getClientId());
                if (channel == null){
                    channel = ctx.channel();
                    ChannelMap.putChannelByClientId(msg.getType(), msg.getClientId(), channel);
                }
                msg.setBaseMsg("client msf=g back " + msg.getBaseMsg());
                channel.writeAndFlush(msg);
//                channel.close(); // 通道关闭了后，就不能再发了
                break;
            case ASK:// 客户端ask - me something
                break;
            case REPLY:// 客户端reply - me something
                break;
            case LOGIN:
                LoginMsg loginMsg = (LoginMsg) msg;
                if (loginMsg.getUserName().equals("luke") && loginMsg.getPassword().equals("123456")){
                    System.out.println("receive the login msg");// 收到客户端的ping消息需要发给客户端
                    channel = ChannelMap.getChannelByClientId(msg.getType(), msg.getClientId());
                    if (channel == null){
                        channel = ctx.channel();
                        ChannelMap.putChannelByClientId(msg.getType(), msg.getClientId(), channel);
                    }
                    msg.setBaseMsg("login msg from the server: okay = " + msg.getBaseMsg());
                    channel.writeAndFlush(msg);
                }else {
                    msg.setBaseMsg("login msg failed ..: not okay = " + msg.getBaseMsg());
                    ctx.channel().writeAndFlush(msg);
                }
                break;
            default:
                throw new IllegalArgumentException("cannot recongnize the msgType : " + msg.getType().toString());

        }
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("the msgHandler means that the netty client has already miss the connection with the server.");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("the msgHandler tell us the netty client has already connected the server.");
    }
}
