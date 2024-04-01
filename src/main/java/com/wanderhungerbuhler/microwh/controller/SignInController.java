package com.wanderhungerbuhler.microwh.controller;


import com.wanderhungerbuhler.microwh.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("signin");
        mv.addObject("signin", new User());
        return mv;
    }

    @GetMapping("/signin")
    public ModelAndView signin() {
        ModelAndView mv = new ModelAndView("signin");
        mv.addObject("signin", new User());
        return mv;
    }
}
