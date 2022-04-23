package com.example.demo.Service.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Dao.TeamMapper;
import com.example.demo.Dao.TransMapper;
import com.example.demo.Dao.TranshandleMapper;
import com.example.demo.Dao.UserMapper;
import com.example.demo.Dao.UserinfoMapper;
import com.example.demo.Model.Team;
import com.example.demo.Model.Trans;
import com.example.demo.Model.User;
import com.example.demo.Model.Userinfo;
import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.TeamService;

import org.springframework.beans.factory.annotation.Autowired;

public class TeamServiceimpl implements TeamService {

    @Autowired
    UserMapper usermapper;
    @Autowired
    UserinfoMapper userinfoMapper;
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    TranshandleMapper transhandleMapper;
    @Autowired
    TransMapper transMapper;

    private Msg msg;

    @Override
    public Msg RegisterTeamService(HttpServletRequest request) throws IOException {
        var user = (User) request.getSession().getAttribute("user");
        int userid = user.getUserid();

        StringBuilder data = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line = null;
        while (null != (line = reader.readLine()))
            data.append(line);
        String[] list = data.toString().split("[\\&\\=]");
        String teamname = list[1];
        String teampassword = list[3];
        data = null;
        reader = null;

        int teamid = 0;
        boolean id_increment_flg = true;
        List<Team> teamlist = teamMapper.getTeamList();

        for (var teamtmp : teamlist) {
            if (id_increment_flg) {
                teamid++;
                if (teamid != teamtmp.getTeamid())
                    id_increment_flg = false;
            }
            if (teamtmp.getTeamname().equals(teamname)) {
                msg = Msg.TEAMNAME_EXIST;
                return msg;
            }
        }
        teamid++;

        Team team = new Team();
        team.setTeamid(teamid);
        team.setTeamname(teamname);
        team.setTeampassword(teampassword);
        int ret = teamMapper.insertTeam(team);
        assert (ret > 0);

        Userinfo userinfo = new Userinfo();
        userinfo.setUserid(userid);
        userinfo.setTeamid(teamid);
        userinfo.setIsleader(true);
        ret = userinfoMapper.insertUserinfo(userinfo);
        assert (ret > 0);

        msg = Msg.REGISTERTEAM_SUCC;
        return msg;
    }

    @Override
    public Msg JoinTeamService(HttpServletRequest request) throws IOException {
        var user = (User) request.getSession().getAttribute("user");
        int userid = user.getUserid();

        StringBuilder data = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line = null;
        while (null != (line = reader.readLine()))
            data.append(line);
        String[] list = data.toString().split("[\\&\\=]");
        String teamname = list[1];
        String teampassword = list[3];
        data = null;
        reader = null;

        List<Team> teamlist = teamMapper.getTeamList();
        for (var teamtmp : teamlist) {
            if (teamtmp.getTeamname().equals(teamname)) {
                if (teamtmp.getTeampassword().equals(teampassword)) {
                    Userinfo userinfo = new Userinfo();
                    userinfo.setUserid(userid);
                    userinfo.setTeamid(teamtmp.getTeamid());
                    userinfo.setIsleader(false);
                    int ret = userinfoMapper.insertUserinfo(userinfo);
                    assert (ret > 0);
                    msg = Msg.JOINTEAM_SUCC;
                    return msg;
                } else {
                    break;
                }
            }
        }
        msg = Msg.JOINTEAM_FAIL;

        return msg;
    }

    @Override
    public Msg DisbandTeamService(HttpServletRequest request) throws IOException {
        var user = (User) request.getSession().getAttribute("user");
        int userid = user.getUserid();

        StringBuilder data = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line = null;
        while (null != (line = reader.readLine()))
            data.append(line);
        String[] list = data.toString().split("[\\&\\=]");
        String teamname = list[1];
        String teampassword = list[3];
        data = null;
        reader = null;

        int teamid=0;
        msg=Msg.DISBANDTEAM_FAIL;
        List<Team> teamlist = teamMapper.getTeamList();
        for(var teamtmp:teamlist){
            if(teamtmp.getTeamname().equals(teamname)){
                if(teamtmp.getTeampassword().equals(teampassword)){
                    Userinfo userinfo=new Userinfo();
                    userinfo.setUserid(userid);
                    userinfo.setTeamid(teamtmp.getTeamid());
                    Userinfo userinforet=userinfoMapper.getUserinfoByObj(userinfo);
                    if(userinforet.isLeader()){
                        msg=Msg.DISBANDTEAM_SUCC;
                        teamid=teamtmp.getTeamid();
                        break;
                    }
                    else break;
                }
                else break;
            }
        }

        
        if(msg.equals(Msg.DISBANDTEAM_SUCC)){
            assert(teamid>0);
            List<Integer> transidlist =new ArrayList<>();
            List<Trans> translist=transMapper.getTransListByTeamid(teamid);
            for(var trantmp:translist){
                if(!transidlist.contains(trantmp.getTransid())) transidlist.add(trantmp.getTransid());
            }

            for(var transid:transidlist){
                int ret=transhandleMapper.deleteTranshandleByTransid(transid);
                assert(ret>0);
            }
            
            int ret=transMapper.deleteTransByTeamid(teamid);
            assert(ret>0);

            ret=userinfoMapper.deleteUserinfoByTeamid(teamid);
            assert(ret>0);

            ret=teamMapper.deleteTeam(teamid);
            assert(ret>0);
        }

        return msg;
    }

}
