package com.netty.longconnect;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/21 22:13
 * @since 1.0
 */
public class ReplyMsg extends BaseMsg{
    public ReplyMsg(String baseMsg) {
        super(baseMsg);
    }

    @Override
    public MsgType getType() {
        return MsgType.REPLY;
    }
}
