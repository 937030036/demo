/*
 * @Description: Intercepter for signing in.
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-06 21:07:32
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 22:37:03
 */

package com.example.demo.Intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.demo.Model.User;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyIntercepter implements HandlerInterceptor {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MyIntercepter.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    /**
     * @description: Check session of current request.
     * @param {HttpServletRequest}  request
     * @param {HttpServletResponse} response
     * @param {Object}              handler
     * @return {boolean}
     * @author: Zhangchunhao
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            logger.info(request.getRemoteAddr() + "由于未登录被拦截");
            request.getSession().setAttribute("msg", "未登录或异地登录");
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        return true;
    }

}
