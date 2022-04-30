/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-30 13:19:11
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-30 21:33:16
 */
package com.example.demo.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.TransService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class TransCtler {

    @Autowired
    private TransService transService;

    private Msg msg;

    @PostMapping("Trans")
    public void launchTrans(HttpServletRequest request, HttpServletResponse response) throws IOException {
        msg = transService.LaunchTransService(request);
        request.getSession().setAttribute("msg", msg.toString());

        response.sendRedirect(request.getContextPath() + "Trans/page");
    }

    @PutMapping("Trans/id")
    public void handleTrans(HttpServletRequest request, HttpServletResponse response) throws IOException {
        msg = transService.TransHandleService(request);
        request.getSession().setAttribute("msg", msg.toString());

        response.sendRedirect(request.getContextPath() + "page");
    }

    @PutMapping("Trans/history/id")
    public void alterHistoryTrans(HttpServletRequest request, HttpServletResponse response) throws IOException {
        msg = transService.TransHistoryService(request);
        request.getSession().setAttribute("msg", msg.toString());

        response.sendRedirect(request.getContextPath() + "/Trans/page");
    }
}
