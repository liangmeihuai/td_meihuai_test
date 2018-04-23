package com.netty.longconnect;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/21 22:07
 * @since 1.0
 */
public class PingMsg extends BaseMsg{
    public PingMsg(String baseMsg, String clientId) {
        super(baseMsg, clientId);
    }
    @Override
    public MsgType getType() {
        return MsgType.PING;
    }
}
