package com.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by tend on 2018/3/5.
 */
public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String host = inetAddress.getHostAddress();
        System.out.println("host = [ " + host + " ]");
    }
}
