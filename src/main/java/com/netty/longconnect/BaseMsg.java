package com.netty.longconnect;

import java.io.Serializable;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/21 21:58
 * @since 1.0
 */
// 必须实现序列,serialVersionUID 一定要有,否者在netty消息序列化反序列化
// 会有问题，接收不到消息
public abstract class BaseMsg implements Serializable{
    private static final long serialVersionUID = -466213043362785341L;
    private MsgType type;
    private String clientId; // 客户端的Id
    // baseMsg，其实这个看情况，如何子类发送的不是字符串怎么办
    // 所以不一定有这个属性
    private String baseMsg = "baseMsg";

    public BaseMsg(String baseMsg, String clientId) {
        this.baseMsg = baseMsg;
        this.clientId = clientId;
    }

    public BaseMsg(String baseMsg) {
        this.baseMsg = baseMsg;
    }

    public abstract MsgType getType();


    public void setType(MsgType type) {
        this.type = type;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getBaseMsg() {
        return baseMsg;
    }

    public void setBaseMsg(String baseMsg) {
        this.baseMsg = baseMsg;
    }
}
