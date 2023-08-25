package com.hao.todolistbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        //跨域配置：不可设置为*，不安全, 前后端分离项目，可能域名不一致（前端是8080，后端是8888）
        //注意我们用了nginx之后就不需要写端口号了：配置跨域，如果有多个域名就写多个
        registry.addMapping("/**").allowedOrigins("https://fishbones.com.cn");
//        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }

}
