package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class WelcomeCtrler {

    @RequestMapping("")
    public String Welcome() {
        return "welcome";
    }
}
