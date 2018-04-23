package com.netty.longconnect;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/21 22:13
 * @since 1.0
 */
public class LoginMsg extends BaseMsg{
    private String userName;
    private String password;

    public LoginMsg(String baseMsg, String userName, String password, String clientId) {
        super(baseMsg, clientId);
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public MsgType getType() {
        return MsgType.LOGIN;
    }
}
