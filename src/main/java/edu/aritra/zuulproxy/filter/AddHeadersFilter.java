package edu.aritra.zuulproxy.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import edu.aritra.zuulproxy.login.LoginManager;
import edu.aritra.zuulproxy.login.User;

@Component
public class AddHeadersFilter extends ZuulFilter {

    @Autowired
    LoginManager loginManager;

    public Object run() throws ZuulException {

        if (loginManager.getLoggedinUser() != null) {
            User loggedinUser = loginManager.getLoggedinUser();
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.addZuulRequestHeader("User-Name", loggedinUser.getName());
            ctx.addZuulRequestHeader("User-Email", loggedinUser.getEmail());
            ctx.addZuulRequestHeader("User-Role", loggedinUser.getRole());
        }

        return null;
    }

    public String filterType() {
        return null;
    }

    public int filterOrder() {
        return 0;
    }

    public boolean shouldFilter() {
        return false;
    }
}
