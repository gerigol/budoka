package com.gerigol.budoka.controller.pagecontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping
@Controller
public class StaticPageController {

    @GetMapping("/register")
    public String showRegistration() {
        return "register";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

}
