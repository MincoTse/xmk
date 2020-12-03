package com.minco.zhushou.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.swing.*;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/6/23 20:32
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);


        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        //添加拦截器的 话需要排除 这些静态资源目录 示例：registry.addInterceptor(securityInteceptor()).excludePathPatterns("/css/**", "/js/**", "/font/**", "/images/**", "/resources/**", "/themes/**");
        registry.addResourceHandler("/static/src/**").addResourceLocations("classpath:/static/src/");
        registry.addResourceHandler("/static/tmpl/**").addResourceLocations("classpath:/static/tmpl/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/sui/")
                .setViewName("forward:/swagger-ui/index.html");
    }


}
