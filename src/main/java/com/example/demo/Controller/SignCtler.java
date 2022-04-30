/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-06 16:58:04
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-30 13:46:23
 */
package com.example.demo.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.BeanUtils;
import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.SignService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class SignCtler {
    private static Msg msg;

    private SignService signservice = BeanUtils.getBean(SignService.class);

    @RequestMapping(value = "/Signin/page", method = RequestMethod.GET)
    public String signIn() {
        return "./users/login";
    }

    @RequestMapping(value = "/SigninHandle", method = RequestMethod.GET)
    public void signinHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {

        msg = signservice.signinHandleService(request);
        if (msg.equals(Msg.USERNAME_NOEXIST)) {
            request.getSession().setAttribute("msg", msg.toString());
            response.sendRedirect(request.getContextPath() + "Signup/page");
            return;
        } else if (msg.equals(Msg.PASSWORD_WRONG)) {
            request.getSession().setAttribute("msg", msg.toString());
            response.sendRedirect(request.getContextPath() + "Signin/page");
            return;
        } else if (msg.equals(Msg.SIGNIN_SUCC)) {
            request.getSession().setAttribute("msg", msg.toString());
            response.sendRedirect(request.getContextPath() + "Index/page");
            return;
        }

        throw new IllegalArgumentException("登录异常");
    }

    @RequestMapping(value = "/Signup/page", method = RequestMethod.GET)
    public String SignUp() {
        return "./users/signup";
    }

    @RequestMapping(value = "/User", method = RequestMethod.POST)
    public void signupHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {

        msg = signservice.signupHandleService(request);
        if (msg.equals(Msg.USERNAME_EXIST)) {
            request.getSession().setAttribute("msg", msg.toString());
            response.sendRedirect(request.getContextPath() + "Signup/page");
            return;
        } else if (msg.equals(Msg.SIGNUP_SUCC)) {
            request.getSession().setAttribute("msg", msg.toString());
            response.sendRedirect(request.getContextPath() + "Signin/page");
            return;
        }
        throw new IllegalArgumentException("注册异常");
    }

}
