package com.example.demo.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.BeanUtils;
import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.SignService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/Sign")
public class UserSignCtler {
    private static Msg msg;

    private SignService signservice = BeanUtils.getBean(SignService.class);

    @RequestMapping("/SignIn")
    public String signIn() {
        return "./users/login";
    }

    // @RequestMapping("/GetMsg")
    // public String showMsg() {
    // String tmp = msg.getpath();
    // msg = Msg.NO_MSG;
    // return tmp;
    // }

    @RequestMapping("/SigninHandle")
    public void signinHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {

        msg = signservice.signinHandleService(request);
        if (msg.equals(Msg.USERNAME_NOEXIST)) {
            request.getSession().setAttribute("msg", msg.toString());
            response.sendRedirect(request.getContextPath() + "SignUp");
            return;
        } else if (msg.equals(Msg.PASSWORD_WRONG)) {
            request.getSession().setAttribute("msg", msg.toString());
            response.sendRedirect(request.getContextPath() + "SignIn");
            return;
        } else if (msg.equals(Msg.SIGNIN_SUCC)) {
            request.getSession().setAttribute("msg", msg.toString());
            response.sendRedirect(request.getContextPath() + "/user/Meau");
            return;
        }
        throw new IllegalArgumentException("登录异常");
    }

    @RequestMapping("/SignUp")
    public String SignUp() {
        return "./users/signup";
    }

    @RequestMapping("/SignupHandle")
    public void signupHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {

        msg = signservice.signupHandleService(request);
        if (msg.equals(Msg.USERNAME_EXIST)) {
            request.getSession().setAttribute("msg", msg.toString());
            response.sendRedirect(request.getContextPath() + "SignUp");
            return;
        } else if (msg.equals(Msg.SIGNUP_SUCC)) {
            request.getSession().setAttribute("msg", msg.toString());
            response.sendRedirect(request.getContextPath() + "SignIn");
            return;
        }
        throw new IllegalArgumentException("注册异常");
    }

}
