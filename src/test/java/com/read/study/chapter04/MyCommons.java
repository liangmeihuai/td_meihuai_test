package com.read.study.chapter04;

import com.read.study.chapter04.enums.SendTypeEnum;
import com.read.study.chapter04.sender.MyDefaultSender;
import com.read.study.chapter04.sender.MyMessageSender;
import com.read.study.chapter04.sender.MySendable;
import com.specialtroops.chapter04.socket.client.sender.*;
import sun.reflect.Reflection;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/22 18:03
 * @since 1.0
 */
public class MyCommons {
    public static final String DEFAULT_CHARSET = "utf-8";
    public static final String SERVICE_UPLOAD_PATH = "D:\\meihuai\\alluxio\\specailtroops\\upload";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final static SimpleDateFormat DATE_FORMAT_OBJECT = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
    private final static Map<String , Class<? extends MySendable>> ORDER_CLASS_MAP
            = new HashMap<String , Class<? extends MySendable>>() {
        private static final long serialVersionUID = 3431099761909680054L;
        {
            put(SendTypeEnum.SEND_MSG.getTypeName().toLowerCase(), MyMessageSender.class);
//            put(SendTypeEnum.SEND_FILE.getTypeName().toLowerCase() , FileSender.class);
//            put(SendTypeEnum.SEND_BFILE.getTypeName().toLowerCase() , BFileSender.class);
//            put(SendTypeEnum.GET_FILE.getTypeName().toLowerCase() , GetFileSender.class);
        }
    };

    public static Class<? extends MySendable> findMySendableClassByOrder(String order){
        Class<? extends MySendable> clazz = ORDER_CLASS_MAP.get(order.toLowerCase());
        return clazz == null ? MyDefaultSender.class : clazz;
    }

    public static void print(String str) {
        System.out.print(str);
    }

    public static void println(String str) {
        System.out.println(str);
    }

    public static void logInfo(String message) {
        Class <?>clazz = Reflection.getCallerClass(2);
        // Class <?>clazz = Reflection.getCallerClass(); ,不传参数和输入参数2的效果一样，都是返回调用者的类
        String date = DATE_FORMAT_OBJECT.format(Calendar.getInstance().getTime());
        println(date + " [] INFO " + clazz.getName() + " - " + message);
    }
}
