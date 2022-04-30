package com.example.demo.Service.implement;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Dao.TeamMapper;
import com.example.demo.Dao.TransMapper;
import com.example.demo.Dao.TranshandleMapper;
import com.example.demo.Dao.UserMapper;
import com.example.demo.Dao.UserinfoMapper;
import com.example.demo.Model.Transhandle;
import com.example.demo.Model.User;
import com.example.demo.Model.Userinfo;
import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.PageService;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageServiceimpl implements PageService {

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
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);

    @Override
    public Msg IndexPageService(HttpServletRequest request) {
        var user = (User) request.getSession().getAttribute("user");
        int userid = user.getUserid();
        logger.info("得到用户id" + userid);

        List<Userinfo> userinfolist = userinfoMapper.getUserinfoListByUserid(userid);
        List<String> tnamelist = new ArrayList<>();
        for (var userinfo : userinfolist) {
            tnamelist.add(teamMapper.getTeamById(userinfo.getTeamid()).getTeamname());
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teamlist", tnamelist);

        int unfinnum = transhandleMapper.getUnfinishNumByUserid(userid);
        jsonObject.put("unfinnum", unfinnum);

        request.getSession().setAttribute("data", jsonObject.toString());
        msg = Msg.GETINDEXPAGE_SUCC;
        return msg;
    }

    @Override
    public Msg TransPageService(HttpServletRequest request) {
        var user = (User) request.getSession().getAttribute("user");
        int userid = user.getUserid();

        List<Userinfo> userinfolist = userinfoMapper.getUserinfoListByUserid(userid);
        List<Integer> memidarr = new ArrayList<>();
        for (var userinfo : userinfolist) {
            if (!userinfo.isLeader())
                continue;
            List<Userinfo> memidlist = userinfoMapper.getUserinfoListByTeamid(userinfo.getTeamid());
            for (var mem : memidlist) {
                if (!memidarr.contains(mem.getUserid())) {
                    memidarr.add(mem.getUserid());
                }
            }
        }
        if (!memidarr.contains(userid))
            memidarr.add(userid);
        List<Transhandle> finTranslist = new ArrayList<>();
        for (var memid : memidarr) {
            finTranslist.addAll(transhandleMapper.getFinishTranshandleListByUserid(memid));
        }

        List<Transhandle> unfinTranslist = transhandleMapper.getUnfinishTranshandleListByUserid(userid);

        List<JSONObject> fintranslistjson = new ArrayList<>();
        List<JSONObject> unfintranslistjson = new ArrayList<>();

        for (Transhandle fintmp : finTranslist) {
            JSONObject jsontmp = new JSONObject();
            jsontmp.put("transid", fintmp.getTransid());
            jsontmp.put("username", usermapper.getUserById(fintmp.getUserid()).getUsername());
            jsontmp.put("transtype", transMapper.getTransByTransid(fintmp.getTransid()).getTranstype());
            jsontmp.put("value", fintmp.getValue());
            fintranslistjson.add(jsontmp);
        }

        for (Transhandle fintmp : unfinTranslist) {
            JSONObject jsontmp = new JSONObject();
            jsontmp.put("transid", fintmp.getTransid());
            jsontmp.put("transtype", transMapper.getTransByTransid(fintmp.getTransid()).getTranstype());
            jsontmp.put("value", fintmp.getValue());
            unfintranslistjson.add(jsontmp);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("finlist", fintranslistjson);
        jsonObject.put("unfinlist", unfintranslistjson);

        request.getSession().setAttribute("data", jsonObject.toString());
        msg = Msg.GETTRANSPAGE_SUCC;
        return msg;
    }

    @Override
    public Msg TeamMemPageService(HttpServletRequest request) {
        var user = (User) request.getSession().getAttribute("user");
        int userid = user.getUserid();

        List<Userinfo> userinfolist = userinfoMapper.getUserinfoListByUserid(userid);
        List<List<String>> namelist= new ArrayList<>();

        for(var userinfotmp:userinfolist){
            List<String> sameteammem=new ArrayList<>();
            sameteammem.add(teamMapper.getTeamById(userinfotmp.getTeamid()).getTeamname());

            List<Userinfo> sameteamuserinfos=userinfoMapper.getUserinfoListByTeamid(userinfotmp.getTeamid());
            for(var userinfo:sameteamuserinfos){
                if(userinfo.isLeader()) sameteammem.add("*"+usermapper.getUserById(userinfo.getUserid()).getUsername());
                else sameteammem.add(usermapper.getUserById(userinfo.getUserid()).getUsername());
            }
            namelist.add(sameteammem);
        }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("memlist", namelist);

        request.getSession().setAttribute("data", jsonObject.toString());
        msg = Msg.GETTEAMMEMPAGE_SUCC;
        return msg;
    }

}
