package com.example.rebuild.config;

import com.example.rebuild.common.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 拦截请求
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/data/**")
                .excludePathPatterns("/downloadFile/**");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解决定是否需要登录
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/data/**").addResourceLocations("classpath:/data/");
        registry.addResourceHandler("/downloadFile/**").addResourceLocations("file:/uploads/");
//        registry.addResourceHandler("/downloadFile/**").addResourceLocations("file:G:/uploads/");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

}
