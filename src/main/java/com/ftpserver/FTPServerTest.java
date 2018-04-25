package com.ftpserver;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/25 11:04
 * @since 1.0
 */
public class FTPServerTest {
    public static final FtpServerFactory   serverFactory = new FtpServerFactory();

    public static void main(String[] args) throws FtpException {
        ListenerFactory factory = new ListenerFactory();
        //设置监听端口
        factory.setPort(2121);

        //替换默认监听
        serverFactory.addListener("default", factory.createListener());

        //用户名
        BaseUser user = new BaseUser();
        user.setName("admin1");
        //密码 如果不设置密码就是匿名用户
        user.setPassword("123456");
        //用户主目录
        user.setHomeDirectory("D:\\meihuai");

        List<Authority> authorities = new ArrayList<Authority>();
        //增加写权限
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);

        //增加该用户
        serverFactory.getUserManager().save(user);

        /**
         * 也可以使用配置文件来管理用户
         */
//      PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
//      userManagerFactory.setFile(new File("users.properties"));
//      serverFactory.setUserManager(userManagerFactory.createUserManager());

        FtpServer server = serverFactory.createServer();
        server.start();
    }
}
