package com.wanderhungerbuhler.microwh.controller;

import com.wanderhungerbuhler.microwh.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpController {
    @GetMapping("/signup")
    public ModelAndView signup() {
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("signup", new User());
        return mv;
    }
}
