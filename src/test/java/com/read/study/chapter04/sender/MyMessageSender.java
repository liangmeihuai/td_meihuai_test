package com.read.study.chapter04.sender;

import com.read.study.chapter04.MyCommons;
import com.read.study.chapter04.MySocketWrapper;
import com.read.study.chapter04.enums.SendTypeEnum;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.specialtroops.chapter04.socket.Commons.logInfo;
import static com.specialtroops.chapter04.socket.Commons.print;
import static com.specialtroops.chapter04.socket.Commons.println;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/22 18:18
 * @since 1.0
 */
public class MyMessageSender implements MySendable {
    private int length;
    private String message;
    private byte[] messageBytes;

    public MyMessageSender(String[] tokens) throws UnsupportedEncodingException {
        if (tokens.length >=2){
            this.message = tokens[1];
            this.messageBytes = message.getBytes(MyCommons.DEFAULT_CHARSET);
            this.length = messageBytes.length;
        }else {
            throw new RuntimeException("请在sendMsg后面添加内容");
        }
    }

    @Override
    public int getSendType() {
        return SendTypeEnum.SEND_MSG.getTypeCode();
    }

    @Override
    public void sendContent(MySocketWrapper socketWrapper) throws IOException {
        println("我此时向服务器发送的消息为: " + this.message);
        socketWrapper.write(this.length);
        socketWrapper.write(messageBytes,length);
        println("发送消息完毕");
    }
    // 0  和小于0  -   返回 Reflection类 ,1-返回自己，2返回调用者，3返回调用者的调用者，4---,null返回调用者
    public static void main(String[] args) {
        logInfo("test,Caller");
    }
}
