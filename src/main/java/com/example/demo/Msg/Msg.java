package com.example.demo.Msg;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public enum Msg {
    SIGNIN_SUCC("登录成功"),
    SIGNUP_SUCC("注册成功"),
    USERNAME_EXIST("用户名已存在,请重试"),
    USERNAME_NOEXIST("用户名不存在，请注册"),
    PASSWORD_WRONG("密码错误，请重试"),
    NO_MSG("");

    private String msg_str;

    private Msg(String msg) {
        msg_str = new String(msg);
    }

    public String toString() {
        return msg_str;
    }
}
