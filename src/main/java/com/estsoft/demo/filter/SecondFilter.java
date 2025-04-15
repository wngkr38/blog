package com.estsoft.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("SecondFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String queryString = httpServletRequest.getQueryString();

        log.info("secondFilter - queryString: {}", queryString);
        chain.doFilter(request, response);

        log.info("SecondFilter response");
    }

    @Override
    public void destroy() {
        log.info("SecondFilter destroy");
    }
}
