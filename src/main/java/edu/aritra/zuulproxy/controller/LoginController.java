package edu.aritra.zuulproxy.controller;

import edu.aritra.zuulproxy.login.LoginManager;
import edu.aritra.zuulproxy.login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    LoginManager loginManager;

    @GetMapping(path = "/login")
    public ResponseEntity<User> login(@RequestParam String email) {
        loginManager.login(email);

        if (loginManager.getLoggedinUser() != null) {
            return new ResponseEntity<>(loginManager.getLoggedinUser(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(new User(), HttpStatus.ACCEPTED);
        }
    }
}
