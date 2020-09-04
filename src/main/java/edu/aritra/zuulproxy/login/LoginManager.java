package edu.aritra.zuulproxy.login;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoginManager {

    private List<User> users;
    private User loggedinUser;


    public void login(final String email) {
        Optional<User> existingUser = users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
        existingUser.ifPresent(this::setLoggedinUser);
    }

    public User getLoggedinUser() {
        return loggedinUser;
    }

    private void setLoggedinUser(User loggedinUser) {
        this.loggedinUser = loggedinUser;
    }

    @PostConstruct
    private void loadUsers() throws IOException {
        File usersFile = new ClassPathResource("users.json").getFile();
        ObjectMapper mapper = new ObjectMapper();
        users = Arrays.asList(mapper.readValue(usersFile, User[].class));
    }

    List<User> getUsers() {
        return users;
    }
}
