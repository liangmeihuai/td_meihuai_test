package com.read.study.chapter04.sender;

import com.read.study.chapter04.MyCommons;
import com.read.study.chapter04.MySocketWrapper;
import com.read.study.chapter04.enums.SendTypeEnum;
import com.read.study.chapter04.exceptions.MyExitException;

import java.io.IOException;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/23 11:54
 * @since 1.0
 */
public class MyDefaultSender implements MySendable{
    public MyDefaultSender(String[] tokens) {
        String order = tokens[0];
        if (SendTypeEnum.HELP.getTypeName().equalsIgnoreCase(order)){
            MyCommons.println(SendTypeEnum.HELP.getDesc());
        }else if (SendTypeEnum.EXIT.getTypeName().equalsIgnoreCase(order)){
            throw new MyExitException("the client has exit");
        }else {
            throw new RuntimeException(MySendable.ERROR_MESSAGE_FORMAT);
        }
    }
    @Override
    public int getSendType() {
        return 0;
    }
    @Override
    public void sendContent(MySocketWrapper socketWrapper) throws IOException {
        // default to do something
    }
}
