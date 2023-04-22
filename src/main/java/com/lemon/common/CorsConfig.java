package com.lemon.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName CorsConfig
 * @Description 重写WebMvcConfigurer(全局解决跨域)
 *              另外(局部解决跨域):在控制器(类上)上使用注解 @CrossOrigin(origins = "*"):，表示该类的所有方法允许跨域。
 * @Author lemon
 * @Date 21:30 2023/2/9
 * @Version 2.1
 **/
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowedOriginPatterns("*")
                //放行哪些原始域
                .allowedOrigins("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

}
