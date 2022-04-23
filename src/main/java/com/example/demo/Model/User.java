/*
 * @Description: Model:User,Attribute:userid,username,password.
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-06 21:26:30
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 22:29:54
 */

package com.example.demo.Model;

public class User {
    private int userid;
    private String password;
    private String username;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
