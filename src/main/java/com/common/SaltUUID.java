package com.common;

import org.junit.Test;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * Created by tend on 2017/12/13.
 */
public class SaltUUID {
    private static final String ADMIN_ACCOUNT = "smc@tendcloud.com";
    private static final String READYACCOUNT = "mc_marketing@tendcloud.com";
    private static final String XIANHE_ACCOUNT = "mc_brand@tendcloud.com";
    private static final String TED_ACCOUNT = "mc_sales@tendcloud.com";
    public static void main(String[] args) {
        System.out.println("admin="+MD5Static("SmartMC"+"5afc698a10744a178f806f71ce1c9568"));
        System.out.println("ready="+MD5Static("SmartMCReady"+"a2065036b0014231a8dc30ff24f00070"));
        System.out.println("xianhe="+MD5Static("SmartMCBrand"+"5c1b93b372c84aea8fc3b8476e8919c1"));
        System.out.println("ted="+MD5Static("SmartMCSales"+"23337c20d3b645c99c08e236b02d00ca"));
    }
    @Test
    public void testUUID(){
        System.out.println("s=" + UUID.randomUUID().toString().replaceAll("-",""));
    }


    private static String convertedToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfOfByte = (data[i] >>> 4) & 0x0F;
            int twoHalfBytes = 0;
            do {
                if ((0 <= halfOfByte) && (halfOfByte <= 9)) {
                    buf.append((char) ('0' + halfOfByte));
                } else {
                    buf.append((char) ('a' + (halfOfByte - 10)));
                }
                halfOfByte = data[i] & 0x0F;
            } while (twoHalfBytes++ < 1);
        }
        return buf.toString();
    }
    public  static  String MD5Static(String text) {
        MessageDigest md;
        byte[] md5 = new byte[64];
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            md5 = md.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertedToHex(md5);
    }
}
