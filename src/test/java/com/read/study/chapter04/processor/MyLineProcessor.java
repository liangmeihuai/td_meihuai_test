package com.read.study.chapter04.processor;

import com.read.study.chapter04.MyCommons;
import com.read.study.chapter04.MySocketWrapper;
import com.read.study.chapter04.exceptions.MyNoOptionException;
import com.read.study.chapter04.sender.MySendable;
import com.specialtroops.chapter04.socket.SocketWrapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 解析命令分发命令的一个统一类
 * 一个类用到的属性和某些方法需要某些对象的引用包装完成一些功能
 * 被动的就是方法参数，或者构造函数，属性set进去完成
 * 要不就是静态代码块或者构造代码块里面主动去引用获取一些需要的信息
 *
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/23 11:18
 * @since 1.0
 */
public class MyLineProcessor {
    private MySendable sendable;
    private String[] tokens;

    public MyLineProcessor(String line) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        line = preLine(line);
        if (line == null || line.trim().length() == 0) {
            throw new MyNoOptionException("没有这种操作类型");
        }
        this.tokens = line.split("\\s+");
        String firstToken = tokens[0];
        this.sendable = MyCommons.findMySendableClassByOrder(firstToken).
                getDeclaredConstructor(String[].class).newInstance(new Object[]{tokens});
    }

    public void sendContentBySocket(MySocketWrapper socketWrapper) throws IOException {
        if (this.sendable != null && this.sendable.getSendType() > 0){
            socketWrapper.write(this.sendable.getSendType());
            this.sendable.sendContent(socketWrapper);
        }
    }

    public String preLine(String line) {
        if (line == null) return "";
        if (line.startsWith(">")) return line.substring(1);
        return line;
    }

}
