/*
 * @Description: Model:Team,Attribute:teamid,teamname,teampassword.
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-22 22:39:49
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 22:27:48
 */

package com.example.demo.Model;

public class Team {
    private int teamid;
    private String teamname;
    private String teampassword;

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getTeampassword() {
        return teampassword;
    }

    public void setTeampassword(String teampassword) {
        this.teampassword = teampassword;
    }

}
