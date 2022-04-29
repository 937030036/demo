/*
 * @Description: SignServiceTest
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-06 16:57:18
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-29 13:22:36
 */
package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Dao.TeamMapper;
import com.example.demo.Dao.TransMapper;
import com.example.demo.Dao.TranshandleMapper;
import com.example.demo.Dao.UserMapper;
import com.example.demo.Dao.UserinfoMapper;
import com.example.demo.Model.User;
import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.SignService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignServiceTest {
	@Autowired
	private SignService signService;

	@MockBean(name = "userMapper")
	private UserMapper userMapper;

	@MockBean(name = "userinfoMapper")
	private UserinfoMapper userinfoMapper;

	@MockBean(name = "teamMapper")
	private TeamMapper teamMapper;

	@MockBean(name = "transMapper")
	private TransMapper transMapper;

	@MockBean(name = "transhandleMapper")
	private TranshandleMapper transhandleMapper;

	@BeforeEach
	public void Setup() {

		List<User> userlist = new ArrayList<>();
		User user = new User();
		user.setUserid(55);
		user.setUsername("aa");
		user.setPassword("bb");
		userlist.add(user);
		Mockito.when(userMapper.getUserList()).thenReturn(userlist);
	}

	@Test
	public void Mocktest() throws IOException {
		MockHttpServletRequest Request = new MockHttpServletRequest();
		Request.addParameter("username", "aaa");
		Request.addParameter("password", "bb");
		Msg msg;

		msg = signService.signinHandleService(Request);

		User user = (User) Request.getSession().getAttribute("user");
		assert (user.getUserid() == 55);
		assert (user.getUsername().equals("aaa"));
		assert (user.getPassword().equals("bb"));
		assert (msg.equals(Msg.SIGNIN_SUCC));

	}
}
