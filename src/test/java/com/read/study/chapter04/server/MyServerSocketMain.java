package com.read.study.chapter04.server;

import com.read.study.chapter04.MyCommons;
import com.read.study.chapter04.MySocketWrapper;
import com.specialtroops.chapter04.socket.Commons;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/23 14:01
 * @since 1.0
 */
public class MyServerSocketMain {
    private static  final List<MyWorker> workers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        initPath();
        // 初始化上传文件存放的目录
        ServerSocket serverSocket = new ServerSocket(8888);
        MyCommons.println("服务端口是8088，开始准备接受数据....");

        try{
            int index = 1;
            while (true){
                MySocketWrapper socketWrapper =  new MySocketWrapper(serverSocket.accept());
                workers.add(new MyWorker(socketWrapper, "server-thread-name " +  index ++));
            }
        }finally {
            Commons.closeStream(serverSocket);
            interruptWorkers();
        }
    }

    private static void interruptWorkers(){
        for (MyWorker worker : workers){
            worker.interrupt();
        }
    }

    private static void initPath(){
        File file = new File(MyCommons.SERVICE_UPLOAD_PATH);
        if (!file.exists()){
            boolean success = file.mkdirs();
            if (!success){
                throw new RuntimeException("无法创建服务端的下载目录:" + MyCommons.SERVICE_UPLOAD_PATH);
            }
        }

    }

}
