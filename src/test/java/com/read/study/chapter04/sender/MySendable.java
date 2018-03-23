package com.read.study.chapter04.sender;
import com.read.study.chapter04.MySocketWrapper;
import com.read.study.chapter04.enums.SendTypeEnum;

import java.io.IOException;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/22 17:53
 * @since 1.0
 */
public interface MySendable {
    // 发送时候的错误消息格式
    final String ERROR_MESSAGE_FORMAT = "错误的消息格式，请参看 demo：\n" + SendTypeEnum.HELP.getDesc();
    // 发送得有类型，是sendMsg，sendFile, sendBFile,getFile
    int getSendType();
    // 要发送肯定是客户端发送，肯定需要socketWrapper
    void sendContent(MySocketWrapper socketWrapper) throws IOException;
}
