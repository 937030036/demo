/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-13 17:55:09
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-27 20:38:04
 */
package com.example.demo.Service.implement;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.Dao.UserMapper;
import com.example.demo.Model.User;
import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.SignService;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);
    private static Map<String, HttpSession> userloginMap = new HashMap<>();
    @Autowired
    UserMapper usermapper;

    private Msg msg;

    @Override
    public Msg signinHandleService(HttpServletRequest request) throws IOException {
        List<User> userList = usermapper.getUserList();

        String username = new String((request.getParameter("username")).getBytes("ISO-8859-1"), "UTF-8");
        String password = new String((request.getParameter("password")).getBytes("ISO-8859-1"), "UTF-8");

        for (var user : userList) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    logger.info(user.getUsername() + " login succ!");

                    msg = Msg.SIGNIN_SUCC;

                    if (!userloginMap.isEmpty()) {
                        if (userloginMap.containsKey(username)) {
                            userloginMap.get(username).invalidate();
                        }
                    }
                    userloginMap.put(username, request.getSession());
                    request.getSession().setAttribute("user", user);

                    return msg;
                } else {
                    logger.info(username + " password invaild!");
                    msg = Msg.PASSWORD_WRONG;
                    return msg;
                }
            }
        }
        logger.info(username + " no exist!");
        msg = Msg.USERNAME_NOEXIST;
        return msg;
    }

    @Override
    public Msg signupHandleService(HttpServletRequest request) throws IOException {
        List<User> userList = usermapper.getUserList();

        String username = new String((request.getParameter("username")).getBytes("ISO-8859-1"), "UTF-8");
        String password = new String((request.getParameter("password")).getBytes("ISO-8859-1"), "UTF-8");

        int userid = 0;
        boolean id_increment_flg = true;
        for (var user : userList) {
            if (id_increment_flg) {
                userid++;
                if (user.getUserid() != userid)
                    id_increment_flg = false;

            }
            if (user.getUsername().equals(username)) {
                logger.info(username + " already exists");
                msg = Msg.USERNAME_EXIST;
                return msg;
            }
        }
        if (userid == 0)
            userid = 1;
        User user = new User();
        user.setUserid(userid);
        user.setUsername(username);
        user.setPassword(password);
        int ret = usermapper.insertUser(user);
        assert (ret > 0);
        msg = Msg.SIGNUP_SUCC;

        return msg;
    }

}
