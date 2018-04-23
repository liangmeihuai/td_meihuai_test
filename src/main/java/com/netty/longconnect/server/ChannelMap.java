package com.netty.longconnect.server;

import com.netty.longconnect.MsgType;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/21 22:15
 * @since 1.0
 */
public class ChannelMap {
    private static final ConcurrentHashMap<MsgType, ConcurrentHashMap<String, Channel>> channelMap = new ConcurrentHashMap<>();
    public static void putChannelByClientId(MsgType msgType, String clientId, Channel channel){
        if (channelMap.get(msgType) == null){
            ConcurrentHashMap<String, Channel> concurrentHashMap = new ConcurrentHashMap<>();
            concurrentHashMap.put(clientId, channel);
            channelMap.put(msgType, concurrentHashMap);
        }else {
            channelMap.get(msgType).put(clientId, channel);
        }
    }
    public static Channel getChannelByClientId(MsgType msgType, String clientId){
        ConcurrentHashMap<String, Channel> chMap = channelMap.get(msgType);
        if (chMap == null){
            return null;
        }
        return chMap.get(clientId);
    }
    public static Channel removeChannelByClientId(MsgType msgType, String clientId){
        return channelMap.get(msgType).remove(clientId);
    }
    public static void clearAllChannels(){
        channelMap.clear();
    }
    public static void removeChannelByMsgTypeAndChannel(MsgType msgType, Channel channel){
        ConcurrentHashMap<String, Channel> msgMap = channelMap.get(msgType);
        for (Map.Entry<String, Channel> entry : msgMap.entrySet()){
            String clientId = entry.getKey();
            Channel storedChannel = entry.getValue();
            if (channel == storedChannel){
                msgMap.remove(channel);
            }
        }
    }
}
