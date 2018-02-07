package com.common;

import com.talkingdata.mc.utils.DateUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by tend on 2017/12/18.
 */
public class MillToDate {
    public static void main(String[] args) throws ParseException {
        System.out.println("date1 = " + new Date(1512557856883L));
        System.out.println("date2 = " + new Date(1512921599999L));
        String dateStr1 = "2017-11-14 00:00:00";
        String dateStr = "2018-01-05 23:59:59";
        System.out.println("dateStr1 is [" + dateStr1 + "],And MilliTimes is [" +formatDateStrToDate(dateStr1).getTime()+ "]");
        System.out.println("dateStr is [" + dateStr + "],And MilliTimes is [" +formatDateStrToDate(dateStr).getTime()+ "]");
    }

    private static Date formatDateStrToDate(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(dateStr);
        return date;
    }

    @Test
    public void dateToMillis(){
        System.out.println("start = " + DateUtil.toDate("2018-01-02 00:00:00", "yyyy-MM-dd HH:mm:ss").getTime());
        System.out.println("end = " + DateUtil.toDate("2018-02-28 23:59:59", "yyyy-MM-dd HH:mm:ss").getTime());
    }
}
