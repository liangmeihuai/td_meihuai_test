package com.read.study.chapter04.server;

import com.read.study.chapter04.MyCommons;
import com.read.study.chapter04.MySocketWrapper;
import com.read.study.chapter04.enums.SendTypeEnum;
import com.specialtroops.chapter04.socket.Commons;
import com.specialtroops.chapter04.socket.client.exceptions.DownloadNotExistsFileException;
import com.specialtroops.chapter04.socket.client.exceptions.SaveExistsFileException;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;

import static com.specialtroops.chapter04.socket.Commons.logInfo;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/23 14:10
 * @since 1.0
 */
public class MyWorker extends Thread{
    // 线程得有姓名，还得有需要处理的对象实体，所以在属性里面
    private String name;
    private MySocketWrapper socketWrapper;
    public MyWorker(MySocketWrapper socketWrapper, String name){
        super(name);
        this.name = name;
        this.socketWrapper = socketWrapper;
        logInfo("我是线程：" + name + " , 开始启动接收客户端传来数据......");
        this.start();
    }

    @Override
    public void run() {
        while (true){
            try {
                if (this.isInterrupted()) break;
                int order = socketWrapper.readInt();
                SendTypeEnum sendTypeEnum = SendTypeEnum.getSendTypeByCode(order);
                if (sendTypeEnum == null){
                    logInfo("cannot find the order int this procedure, order is [ " + order + " ]");
                    continue;
                    // 真正开发时候拒绝某一次操作失败的服务
//                    throw new IllegalStateException("cannot find the order in this procedure");
                }
                switch (sendTypeEnum){
                    case SEND_MSG: processMsg();break;
                    case SEND_FILE: break;
                    case SEND_BFILE:  break;
                    case GET_FILE:  break;
                    default:
                }
            }catch(SaveExistsFileException e) {
                logInfo(e.getMessage());
            }catch(DownloadNotExistsFileException e) {
                logInfo(e.getMessage());
            }catch(EOFException e) {
                logInfo("客户端关闭socket，线程 :" + name + " 结束执行.");
                break;//对方socket已经断开
            }catch(SocketException e) {
                logInfo("Socket异常：" + e.getMessage() + "，线程 :" + name + " 结束执行.");
                break;//socket异常
            }catch(Exception e) {
                e.printStackTrace();
                logInfo("线程 :" + name + " 结束执行.");
                break;
            }
        }
        Commons.closeStream(this.socketWrapper);
    }

    private void processMsg() throws IOException {
        int msgLen = socketWrapper.readInt();
        byte[] bytes = new byte[msgLen];
        socketWrapper.readBytes(bytes);
        logInfo("线程 [ " + name + " ]接收到客户端传递过来的消息是: " + new String(bytes, MyCommons.DEFAULT_CHARSET));
    }

    public void interrupt(){
        if (this.isAlive())
            super.interrupt();
    }
}
