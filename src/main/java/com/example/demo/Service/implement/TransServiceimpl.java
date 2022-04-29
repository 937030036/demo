package com.example.demo.Service.implement;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Dao.TeamMapper;
import com.example.demo.Dao.TransMapper;
import com.example.demo.Dao.TranshandleMapper;
import com.example.demo.Dao.UserinfoMapper;
import com.example.demo.Model.Trans;
import com.example.demo.Model.Transhandle;
import com.example.demo.Model.User;
import com.example.demo.Model.Userinfo;
import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.TransService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransServiceimpl implements TransService {

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
    public Msg LaunchTransService(HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        int userid = user.getUserid();

        String teamname = new String((request.getParameter("teamname")).getBytes("ISO-8859-1"), "UTF-8");
        String value = new String((request.getParameter("value")).getBytes("ISO-8859-1"), "UTF-8");
        String transtype = new String((request.getParameter("transtype")).getBytes("ISO-8859-1"), "UTF-8");

        List<Trans> translist = transMapper.getAllTransList();
        int transid = 0;
        boolean id_autoincrement_flg = true;
        for (var transtmp : translist) {
            if (id_autoincrement_flg) {
                transid++;
                if (transtmp.getTransid() != transid) {
                    id_autoincrement_flg = false;
                    break;
                }
            }
        }
        if(transid==0) transid=1;

        int teamid = teamMapper.getTeamIdByName(teamname);

        Userinfo userinfo = new Userinfo();
        userinfo.setUserid(userid);
        userinfo.setTeamid(teamid);
        Userinfo userinforet = userinfoMapper.getUserinfoByObj(userinfo);
        if (!userinforet.isLeader()) {
            msg = Msg.LAUNCHTRANS_FAIL;
            return msg;
        }

        Trans trans = new Trans();
        trans.setTeamid(teamid);
        trans.setTransid(transid);
        trans.setTranstype(transtype);
        trans.setValue(value);

        int ret = transMapper.insertTrans(trans);
        assert (ret > 0);

        List<Userinfo> userinfolist = userinfoMapper.getUserinfoListByTeamid(teamid);
        for (var userinfotmp : userinfolist) {
            Transhandle transhandle = new Transhandle();
            transhandle.setUserid(userinfotmp.getUserid());
            transhandle.setTransid(transid);
            transhandle.setIshandled(false);
            transhandle.setValue(value);
            ret = transhandleMapper.insertTranshandle(transhandle);
            assert (ret > 0);
        }

        msg = Msg.LAUNCHTRANS_SUCC;
        return msg;
    }

    @Override
    public Msg TransHandleService(HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        int userid = user.getUserid();

        String transidstr = new String((request.getParameter("transid")).getBytes("ISO-8859-1"), "UTF-8");
        String value = new String((request.getParameter("value")).getBytes("ISO-8859-1"), "UTF-8");

        int transid = Integer.parseInt(transidstr);

        Transhandle transhandle = new Transhandle();
        transhandle.setUserid(userid);
        transhandle.setTransid(transid);
        transhandle.setIshandled(true);
        transhandle.setValue(value);
        int ret = transhandleMapper.updateTranshandleStatu(transhandle);
        assert (ret > 0);

        msg = Msg.TRANSHANDLE_SUCC;
        return msg;
    }

    @Override
    public Msg TransHistoryService(HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        int userid = user.getUserid();

        String transidstr = new String((request.getParameter("transidstr")).getBytes("ISO-8859-1"), "UTF-8");
        int transid = Integer.parseInt(transidstr);

        Transhandle transhandle = new Transhandle();
        transhandle.setUserid(userid);
        transhandle.setTransid(transid);
        transhandle.setIshandled(false);
        int ret = transhandleMapper.updateTranshandleStatu(transhandle);
        assert (ret > 0);

        msg = Msg.HISTORYTRANS_SUCC;
        return msg;
    }

}
