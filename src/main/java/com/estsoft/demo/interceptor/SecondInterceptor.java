package com.estsoft.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
postHandle() - contoller 호출 직후 처리됨.
: ModelAndView 에서 Model객체 변경 (소문자 -> 대문자)
ex) query -> QUERY
 */
@Slf4j
public class SecondInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("SecondInterceptor preHandle()");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("SecondInterceptor postHandle()");

        if (modelAndView != null) {
            ModelAndView mv = (ModelAndView) modelAndView.getModelMap().get("modelAndView");
            String query = (String) mv.getModel().get("query");

            if (query != null) {
                modelAndView.addObject("query", query.toUpperCase()); // query 값 대문자로 변경
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("SecondInterceptor afterCompletion()");
    }
}
