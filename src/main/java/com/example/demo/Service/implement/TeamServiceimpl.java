package com.example.demo.Service.implement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Dao.TeamMapper;
import com.example.demo.Dao.TransMapper;
import com.example.demo.Dao.TranshandleMapper;
import com.example.demo.Dao.UserinfoMapper;
import com.example.demo.Model.Team;
import com.example.demo.Model.Trans;
import com.example.demo.Model.User;
import com.example.demo.Model.Userinfo;
import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceimpl implements TeamService {

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

        String teamname =request.getParameter("teamname");
        String teampassword = new String((request.getParameter("teampassword")).getBytes("ISO-8859-1"), "UTF-8");

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
        if (id_increment_flg)
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

        String teamname = new String((request.getParameter("teamname")).getBytes("ISO-8859-1"), "UTF-8");
        String teampassword = new String((request.getParameter("teampassword")).getBytes("ISO-8859-1"), "UTF-8");

        List<Team> teamlist = teamMapper.getTeamList();
        Team team;
        try{
            team=teamlist
                .stream()
                .filter(teamtmp->teamtmp.getTeamname().equals(teamname))
                .findAny()
                .get();
        }
        catch(NoSuchElementException e){
            team=null;
        }

        if(team==null){
            return Msg.JOINTEAM_FAIL;
        }
        else{
            if(team.getTeampassword().equals(teampassword)){
                Userinfo userinfo = new Userinfo();
                userinfo.setUserid(userid);
                userinfo.setTeamid(team.getTeamid());
                userinfo.setIsleader(false);
                int ret = userinfoMapper.insertUserinfo(userinfo);
                assert (ret > 0);
                return Msg.JOINTEAM_SUCC;
            }
            else{
                return Msg.JOINTEAM_FAIL;
            }
        }
    }

    @Override
    public Msg DisbandTeamService(HttpServletRequest request) throws IOException {
        var user = (User) request.getSession().getAttribute("user");
        int userid = user.getUserid();

        String teamname = new String((request.getParameter("teamname")).getBytes("ISO-8859-1"), "UTF-8");
        String teampassword = new String((request.getParameter("teampassword")).getBytes("ISO-8859-1"), "UTF-8");

        int teamid = 0;
        msg = Msg.DISBANDTEAM_FAIL;
        List<Team> teamlist = teamMapper.getTeamList();
        Team team;
        try{
            team=teamlist
                .stream()
                .filter(teamtmp->teamtmp.getTeamname().equals(teamname))
                .filter(teamtmp->teamtmp.getTeampassword().equals(teampassword))
                .findAny()
                .get();
        }
        catch(NoSuchElementException e){
            team=null;
        }

        if(team!=null){
            Userinfo userinfo = new Userinfo();
            userinfo.setUserid(userid);
            userinfo.setTeamid(team.getTeamid());
            Userinfo userinforet = userinfoMapper.getUserinfoByObj(userinfo);
            if (userinforet.isLeader()) {
                msg = Msg.DISBANDTEAM_SUCC;
                teamid = team.getTeamid();
            }
        }

        if (msg.equals(Msg.DISBANDTEAM_SUCC)) {
            assert (teamid > 0);
            List<Integer> transidlist = new ArrayList<>();
            List<Trans> translist = transMapper.getTransListByTeamid(teamid);

            translist
            .stream()
            .filter(transtmp->transidlist.contains(transtmp.getTransid()))
            .forEach(transtmp->transidlist.add(transtmp.getTransid()));

            transidlist
            .stream()
            .forEach(transid->transhandleMapper.deleteTranshandleByTransid(transid));

            int ret = transMapper.deleteTransByTeamid(teamid);
            assert (ret > 0);

            ret = userinfoMapper.deleteUserinfoByTeamid(teamid);
            assert (ret > 0);

            ret = teamMapper.deleteTeam(teamid);
            assert (ret > 0);
        }

        return msg;
    }

}
