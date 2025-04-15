package com.estsoft.demo.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FirstInterceptor())
                .addPathPatterns("/test")
                .order(1);

        registry.addInterceptor(new SecondInterceptor())
                .addPathPatterns("/test")
                .order(2);
    }
}
