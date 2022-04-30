/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-30 12:43:54
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-30 21:33:24
 */
package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Msg.Msg;
import com.example.demo.Service.interfaces.PageService;

import org.apache.ibatis.annotations.Flush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "", method = RequestMethod.GET)
public class PageCtler {
    @Autowired
    private PageService pageService;

    private Msg msg;

    @GetMapping(value = "Index/page")
    public String getIndexPage(HttpServletRequest request) {
        msg = pageService.IndexPageService(request);
        
        //request.getSession().setAttribute("msg", msg.toString());
        return "./users/Index";
    }

    @GetMapping("Team/page")
    public String getTeamPage(HttpServletRequest request) {
        msg = pageService.TeamMemPageService(request);
        request.getSession().setAttribute("msg","");
        return "./users/Team";
    }

    @GetMapping("Trans/page")
    public String getTransPage(HttpServletRequest request) {
        msg = pageService.TransPageService(request);
        //request.getSession().setAttribute("msg",msg.toString());
        return "./users/Trans";
    }

}
