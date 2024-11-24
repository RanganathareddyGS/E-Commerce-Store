package com.s13sh.ecommerce.service;

import org.springframework.ui.ModelMap;

import jakarta.servlet.http.HttpSession;

public interface MainService {

    String loadHome(ModelMap map);

    String loadLogin();

    String login(String email, String password, HttpSession session);

    String logout(HttpSession session);
    
}
