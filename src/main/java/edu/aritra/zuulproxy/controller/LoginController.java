package edu.aritra.zuulproxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.aritra.zuulproxy.login.LoginManager;

@RestController
public class LoginController {

    @Autowired
    LoginManager loginManager;

    @GetMapping(path = "/login")
    private void login(@RequestParam String email) {
        loginManager.login(email);
    }
}
