package com.example.demo.Controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.Msg.Msg;
import com.example.demo.Users.Users;
import com.example.demo.Users.UsersList;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/Sign")
public class UserSignCtler {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserSignCtler.class);
    private static Msg msg;

    public static Msg getMsg() {
        return msg;
    }

    public static void setMsg(Msg msg) {
        UserSignCtler.msg = msg;
    }

    @RequestMapping("/SignIn")
    public String signIn() {
        return "./users/login";
    }

    @RequestMapping("/GetMsg")
    public String showMsg() {
        String tmp = msg.getpath();
        msg = Msg.NO_MSG;
        return tmp;
    }

    @RequestMapping("/SigninHandle")
    public void signinHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        if (!UsersList.userlist.containsKey(username)) {
            logger.info(username + " no exist!");
            msg = Msg.USERNAME_NOEXIST;
            response.sendRedirect(request.getContextPath() + "SignUp");
            return;
        } else if (!UsersList.userlist.get(username).equals(password)) {
            logger.info(username + " password invaild!");
            response.sendRedirect(request.getContextPath() + "SignIn");
            msg = Msg.PASSWORD_WRONG;
            return;
        }
        Users user = new Users();
        user.setName(username);
        user.setPsw(password);
        request.getSession().setAttribute("user", user);
        msg = Msg.SIGNIN_SUCC;
        response.sendRedirect(request.getContextPath() + "/user/Meau");
        logger.info(user.getName() + " login succ!");
    }

    @RequestMapping("/SignUp")
    public String SignUp() {
        return "./users/signup";
    }

    @RequestMapping("/SignupHandle")
    public void signupHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        if (UsersList.userlist.containsKey(username)) {
            logger.info(username + " already exists");
            msg = Msg.USERNAME_EXIST;
            response.sendRedirect(request.getContextPath() + "SignUp");
            return;
        }
        UsersList.userlist.put(username, password);
        msg = Msg.SIGNUP_SUCC;
        response.sendRedirect(request.getContextPath() + "SignIn");

    }

}
