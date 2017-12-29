//package com.bitmaplabelproxy;
//
///**
// * Created by tend on 2017/12/29.
// */
//public class BitMapLabelProxyExample {
//    public static void main(String[] args) {
//        LabelProxy label = new LabelProxy("http://172.21.64.97:8090/bitmap/");
//        Map<String,List<String>> portraitDesc = new HashMap<String,List<String>>();
//        List<String> labelList = new ArrayList<String>();
//        labelList.add("030501");
//        labelList.add("030402");
//        labelList.add("030101");
//        labelList.add("030102");
//        labelList.add("030207");
//        labelList.add("030208");
//        labelList.add("030209");
//        labelList.add("030210");
//        labelList.add("030211");
//        labelList.add("030212");
//        labelList.add("02011110");
//        labelList.add("02011111");
//        labelList.add("02011103");
//        labelList.add("02011114");
//        labelList.add("02011104");
//        labelList.add("02011115");
//        labelList.add("02011101");
//        labelList.add("02011112");
//        labelList.add("02011102");
//        labelList.add("02011113");
//        labelList.add("02011107");
//        labelList.add("02011118");
//        labelList.add("02011108");
//        labelList.add("02011119");
//        labelList.add("02011105");
//        labelList.add("02011116");
//        labelList.add("02011106");
//        labelList.add("02011117");
//        labelList.add("02011109");
//        labelList.add("040124");
//        labelList.add("040123");
//        portraitDesc.put("label",labelList);
//        BMDesc segBm = new BMDesc();
//        segBm.setTable("");
//        segBm.setId("ebea9390322443fabf418043ad259184");
//        BMDesc filter = new BMDesc();
//        filter.setId("020114");
//        filter.setTable("tag_td");
//        label.portraitCount(0,portraitDesc,"2017-12-12,2017-12-25",segBm,filter);
//    }
//}
