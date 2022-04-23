/*
 * @Description: Model:Userinfo,Attribute:userid,teamid,isleader.
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-22 22:46:41
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 22:30:39
 */
package com.example.demo.Model;


public class Userinfo {
    private int userid;
    private int teamid;
    private boolean isleader;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    public boolean isLeader() {
        return isleader;
    }

    public void setIsleader(boolean isleader) {
        this.isleader = isleader;
    }

}
