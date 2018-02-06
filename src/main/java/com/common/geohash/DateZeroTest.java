package com.common.geohash;

import com.talkingdata.mc.utils.DateUtil;

/**
 * Created by tend on 2018/2/5.
 */
public class DateZeroTest {
    private static  final  String dateStr = "1000-00-00 00:00:00";
    public static void main(String[] args) {
        System.out.println(DateUtil.toDate(dateStr, "yyyy-MM-dd HH:mm:ss"));
    }
}
