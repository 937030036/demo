/*
 * @Description: Message for global,a media to transform message.
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-07 17:57:14
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 22:32:49
 */

package com.example.demo.Msg;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public enum Msg {
    SIGNIN_SUCC("登录成功"),
    SIGNUP_SUCC("注册成功"),
    USERNAME_EXIST("用户名已存在,请重试"),
    USERNAME_NOEXIST("用户名不存在，请注册"),
    PASSWORD_WRONG("密码错误，请重试"),
    NO_MSG(""),
    GETINDEXPAGE_SUCC("主页面获取成功"),
    GETTRANSPAGE_SUCC("事务界面获取成功"),
    GETTEAMMEMPAGE_SUCC("团队成员获取成功"),
    TEAMNAME_EXIST("团队名已存在"),
    REGISTERTEAM_SUCC("注册团队成功"),
    JOINTEAM_SUCC("加入团队成功"),
    JOINTEAM_FAIL("团队名或密码错误"),
    DISBANDTEAM_SUCC("解散团队成功"),
    DISBANDTEAM_FAIL("解散团队失败，确认团队信息和队长身份");

    private String msg_str;

    private Msg(String msg) {
        msg_str = new String(msg);
    }

    public String toString() {
        return msg_str;
    }
}
