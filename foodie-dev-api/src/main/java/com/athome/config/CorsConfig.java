package com.athome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Description:  配置跨域请求
 * @Author Zengfc
 * @Date 2021/6/29 11:22
 * @Version 1.0
 */
@Configuration
public class CorsConfig {

    public CorsConfig(){

    }

    @Bean
    public CorsFilter corsFilter(){
        // 1. 添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");

        //设置是否发送cookie信息
        config.setAllowCredentials(true);

        //设置允许请求的方式
        config.addAllowedMethod("*");

        //设置允许的header
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**",config);

        return new CorsFilter(corsSource);
    }
}
