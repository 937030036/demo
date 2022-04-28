/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-28 21:20:53
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-28 21:23:59
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
public class TransPageCtler {

    @Autowired
    private PageService pageService;

    private Msg msg;

    @RequestMapping(value = "Trans/page", method = RequestMethod.GET)
    public String getIndexPage(HttpServletRequest request) {
        pageService.TransPageService(request);
        assert (msg.equals(Msg.GETTRANSPAGE_SUCC));
        return "./users/Trans";
    }
}
