package com.ftpserver;

import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/25 11:54
 * @since 1.0
 */
public class ChangeFtpUser {
    public static void main(String[] args) throws FtpException {
        //用户名
        BaseUser user = new BaseUser();
        user.setName("admin2");
        //密码 如果不设置密码就是匿名用户
        user.setPassword("123456");
        //用户主目录
        user.setHomeDirectory("D:\\idcheck");

        List<Authority> authorities = new ArrayList<Authority>();
        //增加写权限
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);
        FTPServerTest.serverFactory.getUserManager().save(user);
    }
}
