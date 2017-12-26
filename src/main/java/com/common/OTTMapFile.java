package com.common;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tend on 2017/12/18.
 */
public class OTTMapFile {
    private static final ConcurrentHashMap<String,String> conHashMap =  new ConcurrentHashMap();
    public static void main(String[] args) {
        if (args.length < 3){
            System.out.println("need to have input and output and match File");
        }
        String input =  args[0];
        String output = args[1];
        String match = args[2];
        System.out.println("input is = " + input + ",and output is " + output + ",match is " + match);
        BufferedReader reader = null;
        BufferedWriter writer = null;
        BufferedReader matchReader = null;
        try{
            reader = new BufferedReader(new FileReader(input));
            writer = new BufferedWriter(new FileWriter(CommonUtil.createFileAndParentDir(output)));
            matchReader = new BufferedReader(new FileReader(match));
            // 这个key为mac明文大写然后md5后小写
            String line = reader.readLine();
            while (line != null){
                if (line.contains("|")){
                    String[] ids = line.split("\\|");
                    for (String id : ids){
                        conHashMap.put(replaceMdColonAndMd5LowerUpperCase(id),id);
                    }
                }else {
                    conHashMap.put(replaceMdColonAndMd5LowerUpperCase(line),line);
                }
                line = reader.readLine();
            }
            System.out.println("conHashMap.size() = " + conHashMap.size());
            // 进行匹配
            String matchLine = matchReader.readLine();
            while (matchLine != null){
                String getId = conHashMap.get(matchLine);
                if (getId != null){
                    writer.write(getId);
                    writer.newLine();
                    writer.flush();
                    System.out.println("matchLine = " + matchLine);
                }
                matchLine = matchReader.readLine();
            }

            System.out.println("the end ......");
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }finally {
            CommonUtil.closeStreams(matchReader,writer,reader);
        }
    }

    public static String replaceMdColonAndMd5LowerUpperCase(String id){
        id = id.replaceAll(":","").toUpperCase();
//        return MD5Static(id).toLowerCase();
        return MD5Static(id).toUpperCase();
    }
    @Test
    public void testSplit(){
        String s = "0a:d8:2f:5f:3c:15";
        System.out.println("replaceMdColonAndMd5LowerUpperCase = " + replaceMdColonAndMd5LowerUpperCase(s));
        System.out.println(Arrays.asList(s.split("\\|")));
        System.out.println(s.contains("|"));
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

}
