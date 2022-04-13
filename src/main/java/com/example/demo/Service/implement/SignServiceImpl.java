package com.example.demo.Service.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Dao.UserlistMapper;
import com.example.demo.Model.User;
import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.SignService;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.var;

@Service
public class SignServiceImpl implements SignService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);
    @Autowired
    UserlistMapper userlistmapper;

    private Msg msg;

    @Override
    public Msg signinHandleService(HttpServletRequest request) throws IOException {
        List<User> userList = userlistmapper.getUserList();

        StringBuilder data = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line = null;
        while (null != (line = reader.readLine()))
            data.append(line);
        String[] list = data.toString().split("[\\&\\=]");
        String username = list[1];
        String password = list[3];
        data = null;
        reader = null;

        for (var user : userList) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    request.getSession().setAttribute("user", user);
                    logger.info(user.getUsername() + " login succ!");
                    msg = Msg.SIGNIN_SUCC;
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
        List<User> userList = userlistmapper.getUserList();

        StringBuilder data = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line = null;
        while (null != (line = reader.readLine()))
            data.append(line);
        String[] list = data.toString().split("[\\&\\=]");
        String username = list[1];
        String password = list[3];
        data = null;
        reader = null;

        for (var user : userList) {
            if (user.getUsername().equals(username)) {
                logger.info(username + " already exists");
                msg = Msg.USERNAME_EXIST;
                return msg;
            }
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int ret = userlistmapper.insertUser(user);
        assert (ret > 0);
        msg = Msg.SIGNUP_SUCC;

        return msg;
    }

}
