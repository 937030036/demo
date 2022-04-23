/*
 * @Description: Model:Trans,Attribute:transid,teamid,transtype,value.
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-22 22:48:26
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 22:28:16
 */

package com.example.demo.Model;

public class Trans {
    private int transid;
    private int teamid;
    private String transtype;
    private String value;

    public int getTransid() {
        return transid;
    }

    public void setTransid(int transid) {
        this.transid = transid;
    }

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    public String getTranstype() {
        return transtype;
    }

    public void setTranstype(String transtype) {
        this.transtype = transtype;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
