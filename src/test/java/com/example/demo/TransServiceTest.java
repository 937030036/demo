/*
 * @Description: TransServiceTest
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-27 17:00:14
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-30 13:47:19
 */
package com.example.demo;

import static org.mockito.ArgumentMatchers.any;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Dao.TeamMapper;
import com.example.demo.Dao.TransMapper;
import com.example.demo.Dao.TranshandleMapper;
import com.example.demo.Dao.UserinfoMapper;
import com.example.demo.Model.Team;
import com.example.demo.Model.Trans;
import com.example.demo.Model.Transhandle;
import com.example.demo.Model.User;
import com.example.demo.Model.Userinfo;
import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.TransService;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransServiceTest {

    @Autowired
	private TransService transService;

    @MockBean(name = "transMapper")
	private TransMapper transMapper;

    @MockBean(name = "teamMapper")
	private TeamMapper teamMapper;

    @MockBean(name = "transhandleMapper")
	private TranshandleMapper transhandleMapper;

    @MockBean(name = "userinfoMapper")
	private UserinfoMapper userinfoMapper;

    @BeforeEach
	public void Setup() {
        List<Trans> TransList= new ArrayList<>();
        Trans Trans1=new Trans();
        Trans1.setTeamid(2);
        Trans1.setTransid(1);
        Trans1.setValue("");
        TransList.add(Trans1);

        Trans Trans2=new Trans();
        Trans2.setTeamid(2);
        Trans2.setTransid(3);
        Trans2.setValue("");
        TransList.add(Trans2);

        Trans Trans3=new Trans();
        Trans3.setTeamid(3);
        Trans3.setTransid(4);
        Trans3.setValue("");
        TransList.add(Trans3);





        List<Team> teamList= new ArrayList<>();
        Team team1=new Team();
        team1.setTeamid(1);
        team1.setTeamname("X");
        team1.setTeampassword("");
        teamList.add(team1);

        Team team2=new Team();
        team2.setTeamid(2);
        team2.setTeamname("Y");
        team2.setTeampassword("");
        teamList.add(team2);

        Team Team3=new Team();
        Team3.setTeamid(3);
        Team3.setTeamname("Z");
        Team3.setTeampassword("");
        teamList.add(Team3);




        List<User> userlist= new ArrayList<>();
        User user1=new User();
        user1.setUserid(1);
        user1.setUsername("11");
        user1.setPassword("111");
        userlist.add(user1);

        User user2=new User();
        user2.setUserid(2);
        user2.setUsername("22");
        user2.setPassword("222");
        userlist.add(user2);

        User user3=new User();
        user3.setUserid(3);
        user3.setUsername("33");
        user3.setPassword("111");
        userlist.add(user3);



        List<Userinfo> userinfolist= new ArrayList<>();
        Userinfo info1=new Userinfo();
        info1.setUserid(1);
        info1.setTeamid(1);
        info1.setIsleader(true);
        userinfolist.add(info1);

        Userinfo info2=new Userinfo();
        info2.setUserid(2);
        info2.setTeamid(1);
        info2.setIsleader(false);
        userinfolist.add(info2);

        Userinfo info3=new Userinfo();
        info3.setUserid(3);
        info3.setTeamid(3);
        info3.setIsleader(false);
        userinfolist.add(info3);





	}
    
    @Test
	public void Translaunchsucc() throws IOException {

        List<Trans> TransList= new ArrayList<>();
        Trans Trans1=new Trans();
        Trans1.setTeamid(2);
        Trans1.setTransid(1);
        Trans1.setValue("");
        TransList.add(Trans1);

        Trans Trans2=new Trans();
        Trans2.setTeamid(2);
        Trans2.setTransid(3);
        Trans2.setValue("");
        TransList.add(Trans2);

        Trans Trans3=new Trans();
        Trans3.setTeamid(3);
        Trans3.setTransid(4);
        Trans3.setValue("");
        TransList.add(Trans3);

       
        Mockito.when(transMapper.getAllTransList()).thenReturn(TransList);

        User usertmp=new User();
        usertmp.setUsername("11");
        usertmp.setUserid(1);

		MockHttpServletRequest Request = new MockHttpServletRequest();
		Request.addParameter("teamname", "X");
		Request.addParameter("value", "ac");
        Request.addParameter("transtype", "ad");
        Request.getSession().setAttribute("user", usertmp);

        //Mockito.when(teamMapper.getTeamIdByName(any(String.class))).thenReturn(1);

        Userinfo infotmp=new Userinfo();
        infotmp.setIsleader(true);
        infotmp.setTeamid(1);
        infotmp.setUserid(1);

        Mockito.when(userinfoMapper.getUserinfoByObj(any(Userinfo.class))).thenReturn(infotmp);

        Mockito.when(transMapper.insertTrans(any(Trans.class))).thenReturn(1);

        Mockito.when(transhandleMapper.insertTranshandle(any(Transhandle.class))).thenReturn(1);

		Msg msg;
		msg = transService.LaunchTransService(Request);
		assert (msg.equals(Msg.LAUNCHTRANS_SUCC));
	}
    @Test
	public void Translaunchfail() throws IOException {

        List<Trans> TransList= new ArrayList<>();
        Trans Trans1=new Trans();
        Trans1.setTeamid(2);
        Trans1.setTransid(1);
        Trans1.setValue("");
        TransList.add(Trans1);

        Trans Trans2=new Trans();
        Trans2.setTeamid(2);
        Trans2.setTransid(3);
        Trans2.setValue("");
        TransList.add(Trans2);

        Trans Trans3=new Trans();
        Trans3.setTeamid(3);
        Trans3.setTransid(4);
        Trans3.setValue("");
        TransList.add(Trans3);

       
        Mockito.when(transMapper.getAllTransList()).thenReturn(TransList);

        User usertmp=new User();
        usertmp.setUsername("33");
        usertmp.setUserid(3);

        MockHttpServletRequest Request = new MockHttpServletRequest();
		Request.addParameter("teamname", "Z");
		Request.addParameter("value", "bc");
        Request.addParameter("transtype", "bd");
        Request.getSession().setAttribute("user", usertmp);

        //Mockito.when(teamMapper.getTeamIdByName(any(String.class))).thenReturn(3);

        Userinfo infotmp=new Userinfo();
        infotmp.setIsleader(false);
        infotmp.setTeamid(3);
        infotmp.setUserid(3);

        Mockito.when(userinfoMapper.getUserinfoByObj(any(Userinfo.class))).thenReturn(infotmp);

        Msg msg;
		msg = transService.LaunchTransService(Request);
		assert (msg.equals(Msg.LAUNCHTRANS_FAIL));
    }
    @Test
	public void transhandlesucc() throws IOException {

        User usertmp=new User();
        usertmp.setUsername("22");
        usertmp.setUserid(2);

        MockHttpServletRequest Request = new MockHttpServletRequest();
        Request.addParameter("transid", "1");
		Request.addParameter("value", "aaaa");
        Request.getSession().setAttribute("user", usertmp);
        

        Mockito.when(transhandleMapper.updateTranshandleStatu(any(Transhandle.class))).thenReturn(1);

        Msg msg;
		msg = transService.TransHandleService(Request);
		assert (msg.equals(Msg.TRANSHANDLE_SUCC));

    }


    @Test
	public void TransHistorysucc() throws IOException {

        User usertmp=new User();
        usertmp.setUsername("22");
        usertmp.setUserid(2);

        MockHttpServletRequest Request = new MockHttpServletRequest();
        Request.addParameter("transidstr", "1");
        Request.getSession().setAttribute("user", usertmp);
        

        Mockito.when(transhandleMapper.updateTranshandleStatu(any(Transhandle.class))).thenReturn(1);

        Msg msg;
		msg = transService.TransHistoryService(Request);
		assert (msg.equals(Msg.HISTORYTRANS_SUCC));

    }
}
