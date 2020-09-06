package edu.aritra.zuulproxy;

import edu.aritra.zuulproxy.filter.AddHeadersFilter;
import edu.aritra.zuulproxy.login.LoginManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulproxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulproxyApplication.class, args);
    }

    @Bean
    public AddHeadersFilter addHeadersFilter(LoginManager loginManager) {
        return new AddHeadersFilter(loginManager);
    }
}
