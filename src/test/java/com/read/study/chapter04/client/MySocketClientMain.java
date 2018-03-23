package com.read.study.chapter04.client;

import com.read.study.chapter04.MyCommons;
import com.read.study.chapter04.MySocketWrapper;
import com.read.study.chapter04.exceptions.MyExitException;
import com.read.study.chapter04.exceptions.MyNoOptionException;
import com.read.study.chapter04.processor.MyLineProcessor;
import com.specialtroops.chapter04.socket.Commons;
import com.specialtroops.chapter04.socket.client.exceptions.DirectNotExistsException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import static com.specialtroops.chapter04.socket.Commons.print;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/23 15:17
 * @since 1.0
 */
public class MySocketClientMain {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        MySocketWrapper socketWrapper = new MySocketWrapper("192.168.56.1", 8888);
        try{
            print("已经连接上服务器端，现在可以输入数据开始通信了.....\n>");
            String line = scanner.nextLine();
            while (!"byte".equals(line)){
               if (line != null){
                   try{
                       MyLineProcessor lineProcessor = new MyLineProcessor(line);
                       lineProcessor.sendContentBySocket(socketWrapper);
                       socketWrapper.displayStatus();
                   } catch (MyExitException e) {
                       break;// exit
                   } catch (MyNoOptionException e) {
                      // do somethings
                   } catch (DirectNotExistsException e) {
                       e.printStackTrace();
                   } catch (RuntimeException e) {
                       e.printStackTrace();
                   }catch(FileNotFoundException e) {
                       System.out.println(e.getMessage());
                   } catch (SocketException e){
                       e.printStackTrace();
                       socketWrapper.displayStatus();
                       System.out.println("socket异常,与服务器断开连接1");
                       break;
                   } catch(Exception e){
                       e.printStackTrace();
                       System.out.println("与服务器断开连接2");
                       break;
                   }
               }
                MyCommons.print(">");
                line = scanner.nextLine();
            }
        }finally {
            Commons.closeStream(socketWrapper);
        }

    }

}
