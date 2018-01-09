package com.listenalltimesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;

/**
 * Created by tend on 2018/1/5.
 */
public class SocketDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(Constant.HOST, Constant.PORT));

        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        // 发送信息到服务端
        writer.println("send the msg to server is [Hello Tcp " +new Date()+ "]");
        writer.flush();


//        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//        String line = null;
//        try{
//            line = reader.readLine();
//        }catch (Throwable throwable){
//            throwable.printStackTrace();
//        }
//        int index = 0;
//        while (line != null){
//            String remoteIp = socket.getInetAddress().getHostAddress();
//            System.out.println("socketDemo the index is [" + index++ + "]receive from the remote ip is [" + remoteIp + "],and the msg is [" + line + "]");
////            line = reader.readLine();
//            line = null;
//        }
        //关闭流
        System.out.println("close stream almost start");
        Thread.sleep(16000);
        System.out.println("close stream almost end");
        writer.close();
//        reader.close();
socket.close();

    }
}
