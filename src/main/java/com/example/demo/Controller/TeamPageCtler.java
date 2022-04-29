/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-29 13:36:19
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-29 13:38:09
 */
package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.PageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class TeamPageCtler {
    
    @Autowired
    private PageService pageService;

    private Msg msg;

    @RequestMapping(value = "Team/page", method = RequestMethod.GET)
    public String getTeamPage(HttpServletRequest request) {
        pageService.TeamMemPageService(request);
        assert (msg.equals(Msg.GETTEAMMEMPAGE_SUCC));
        return "./users/Team";
    }
}
