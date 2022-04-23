/*
 * @Description: Model:Transhandle,Attribute:transid,userid,ishandled,value.
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-22 22:50:05
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 22:29:11
 */

package com.example.demo.Model;

public class Transhandle {
    private int transid;
    private int userid;
    private boolean ishandled;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getTransid() {
        return transid;
    }

    public void setTransid(int transid) {
        this.transid = transid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public boolean isIshandled() {
        return ishandled;
    }

    public void setIshandled(boolean ishandled) {
        this.ishandled = ishandled;
    }

}
