package edu.aritra.zuulproxy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import edu.aritra.zuulproxy.login.LoginManager;
import edu.aritra.zuulproxy.login.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AddHeadersFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(AddHeadersFilter.class);
    LoginManager loginManager;

    public AddHeadersFilter(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("in AddHeadersFilter");

        if (loginManager.getLoggedinUser() != null) {
            logger.info("existing user found, adding authorization headers");

            User loggedinUser = loginManager.getLoggedinUser();
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.addZuulRequestHeader("User-Name", loggedinUser.getName());
            ctx.addZuulRequestHeader("User-Email", loggedinUser.getEmail());
            ctx.addZuulRequestHeader("User-Role", loggedinUser.getRole());
        }

        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }
}
