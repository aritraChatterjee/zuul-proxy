package edu.aritra.zuulproxy.login;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.aritra.zuulproxy.ZuulproxyApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ZuulproxyApplication.class})
class LoginManagerTest {

    @Autowired
    LoginManager loginManager;

    @Test
    void loadUsers() {
        assertEquals(2, loginManager.getUsers().size());
        User user = loginManager.getUsers().get(0);
        assertEquals("Luke Skywalker", user.getName());
    }

    @Test
    void getLoggedinUser() {
        assertNull(loginManager.getLoggedinUser());
        loginManager.login("luke.skywalker@starwars.com");
        assertEquals("Luke Skywalker", loginManager.getLoggedinUser().getName());
    }
}