/*
 * @Description: Intercepter config
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-06 22:26:00
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 22:37:59
 */

package com.example.demo.Config;

import com.example.demo.Intercepter.MyIntercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    @Override
    /**
     * @description: Config a route for intercepter
     * @param {InterceptorRegistry} registry
     * @return {*}
     * @author: Zhangchunhao
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyIntercepter()).addPathPatterns("/user/Meau/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/**");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
