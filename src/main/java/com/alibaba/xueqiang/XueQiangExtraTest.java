package com.alibaba.xueqiang;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tend on 2017/9/25.
 */
public class XueQiangExtraTest {
    public static void func3() throws Exception{
        String name = InetAddress.getLocalHost().getCanonicalHostName();
        System.out.println("name:"+name);
    }

    public static void main(String[] args) throws Exception{
        XueQiangExtraTest.func3();
    }

    public void testNULL(){
        Map<String, Object> map=new HashMap<>();
        double impression_pv_sum=0;
        impression_pv_sum += (double) map.get(Metric.impression_pv_total.toCode());
        System.out.println("impression_pv_sum=" + impression_pv_sum);
    }
}
