/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-06 22:07:42
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-28 17:46:16
 */
package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class WelcomeCtrler {

    @RequestMapping(value = { "Welcome/page", "" }, method = RequestMethod.GET)
    public String getWelcomePage() {
        return "welcome";
    }
}
