package com.eutopia.filter;

import com.eutopia.domain.ResultBean;
import com.eutopia.feign.AuthService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private AuthService authService;

    @Value("${auth.token-header}")
    private String tokenHeader;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader(tokenHeader);
        if (StringUtils.isBlank(token)) {
            ctx.setResponseStatusCode(401);
            ctx.setSendZuulResponse(false);
            return null;
        }
        ResultBean<?> authResult = authService.authentication(token);
        if (authResult.getCode() < 0) {
            ctx.setResponseStatusCode(401);
            ctx.setSendZuulResponse(false);
            return null;
        }
        //验证是否在黑名单内...

        return null;
    }

}
