package com.netty.longconnect;

import java.util.UUID;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/21 22:05
 * @since 1.0
 */
public class Constants {
    public static final int PORT = 9096;
    public static String clientId;
    public static String getClientId() {
        clientId = UUID.randomUUID().toString();
        return clientId;
    }
}
