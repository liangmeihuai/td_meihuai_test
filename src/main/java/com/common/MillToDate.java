package com.common;

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
        String dateStr = "2018-02-16";
        System.out.println("dateStr is [" + dateStr + "],And MilliTimes is [" +formatDateStrToDate(dateStr).getTime()+ "]");
    }

    private static Date formatDateStrToDate(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateStr);
        return date;
    }
}
