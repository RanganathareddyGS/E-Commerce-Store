package com.s13sh.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.s13sh.ecommerce.service.MainService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @GetMapping("/")
    public String loadHome(ModelMap map) {
        return mainService.loadHome(map);
    }

    @GetMapping("/login")
    public String loadLogin() {
        return mainService.loadLogin();
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        return mainService.login(email, password, session);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        return mainService.logout(session);
    }
}
