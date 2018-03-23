package com.read.study.chapter04.enums;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/22 18:05
 * @since 1.0
 */
public enum SendTypeEnum {
    SEND_MSG(1, "sendMsg", "发送简单消息"),SEND_FILE(2, "sendFile", "发送普通文件"),
    SEND_BFILE(3, "sendBFile", "发送二进制文件"), GET_FILE(4, "getFile", "下载文件"),
    EXIT(-1, "exit", "退出命令"),HELP(-2, "help", "\n\n\t\t操作方式：\n" +
            "\t\t1、传送普通的字符串，例如：\"sendMsg 你好啊！\" 即可。\n" +
            "\t\t2、传送文本文件，例如：\"sendFile /home/xieyuooo/a.sql charset=utf-8\"\n" +
            "\t\t3、传送非文本文件，例如：\"sendBFile /home/xieyuooo/aaa.jpg\"\n" +
            "\t\t4、下载文件，例如：\"getFile aaa.jpg d:/download/\" 其中d:/download/为下载到本地的目录，若未指定，则下载到user.dir下面\n" +
            "\t\t5、help 输出相关的使用帮助\n" +
            "\t\t6、exit 退出客户端");
    private int typeCode;
    private String typeName;
    private String desc;

    public String getDesc() {
        return desc;
    }

    SendTypeEnum(int typeCode, String typeName, String desc) {
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.desc = desc;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public static SendTypeEnum getSendTypeByCode(int typeCode){
        for (SendTypeEnum sendTypeEnum : SendTypeEnum.values()){
            if (sendTypeEnum.getTypeCode() == typeCode){
                return sendTypeEnum;
            }
        }
        return null;
    }

    public static SendTypeEnum getSendTypeByName(String typeName){
        for (SendTypeEnum sendTypeEnum : SendTypeEnum.values()){
            if (sendTypeEnum.getTypeName().equalsIgnoreCase(typeName) ){
                return sendTypeEnum;
            }
        }
        return null;
    }
}
