package com.netty.longconnect;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/21 21:38
 * @since 1.0
 */
// 这个枚举类应该放在commons-jar包公共模块，客户端和服务端都有
public enum MsgType {
    PING, ASK, REPLY, LOGIN;
}
