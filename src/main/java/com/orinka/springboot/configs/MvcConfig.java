package com.orinka.springboot.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.orinka.springboot")
public class MvcConfig implements WebMvcConfigurer {
   public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/users").setViewName("user");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");
    }
}
