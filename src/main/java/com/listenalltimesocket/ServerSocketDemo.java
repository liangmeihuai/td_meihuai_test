package com.listenalltimesocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tend on 2018/1/5.
 */
public class ServerSocketDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(Constant.PORT);
//        serverSocket.setReuseAddress(true);
        System.out.println("server start...");
        Socket socket = null;

    try{
        while (true){
            socket = serverSocket.accept();
            System.out.println("server accept...");
            Thread.sleep(60000);
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // 发送信息到客户端
//            System.out.println("send to the client begin.");
//            writer.print("the msg you send to me is [" + "aaa" + "]");
//            writer.flush();
            System.out.println("send to the client end.");
            String line = bufferedReader.readLine();
            int index = 0;
            while (line != null){
                String remoteIp = socket.getInetAddress().getHostAddress();
                System.out.println("the index is [" + index++ + "]receive from the remote ip is [" + remoteIp + "],and the msg is [" + line + "]");
//            line = bufferedReader.readLine();
                line = null;
            }

            // 关闭流
            writer.close();
            bufferedReader.close();
//        socket.close();

            // 服务端的close
            //  serverSocket.close();

        }
    }catch (Throwable throwable){
        throwable.printStackTrace();
    }
    }
}
