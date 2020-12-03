package com.minco.zhushou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/6/22 10:24
 */
@Configuration
public class CorsConfig {


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); //注册
        return new CorsFilter(source);
    }


    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //TODO  后面做成配置 支持特定域名
        corsConfiguration.addAllowedOrigin("*"); //允许任何域名
        corsConfiguration.addAllowedHeader("*"); //允许任何头
        corsConfiguration.addAllowedMethod("*"); //允许任何方法
        return corsConfiguration;
    }
}