/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-30 13:18:15
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-30 15:11:43
 */
package com.example.demo.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class TeamCtler {

    @Autowired
    private TeamService teamService;

    private Msg msg;

    @PostMapping(value = "Team")
    public void registerTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        msg = teamService.RegisterTeamService(request);
        request.getSession().setAttribute("msg", msg.toString());

        response.sendRedirect(request.getContextPath() + "Index/page");
    }

    @PutMapping(value = "Team/userid")
    public void joinTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        msg = teamService.JoinTeamService(request);
        request.getSession().setAttribute("msg", msg.toString());
        response.sendRedirect(request.getContextPath() + "/Index/page");
    }

    @DeleteMapping(value = "Team/id")
    public void disbandTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        msg = teamService.DisbandTeamService(request);
        request.getSession().setAttribute("msg", msg.toString());
        response.sendRedirect(request.getContextPath() + "/Index/page");
    }
}
