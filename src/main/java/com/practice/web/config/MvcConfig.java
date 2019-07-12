package com.practice.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/book").setViewName("book");
        registry.addViewController("/bookByCategory").setViewName("bookByCategory");
        registry.addViewController("/category").setViewName("category");
        registry.addViewController("/bookByUser").setViewName("bookByUser");
        registry.addViewController("/userHome").setViewName("userHome");
        registry.addViewController("/searchBook").setViewName("searchBook");
        registry.addViewController("/buyBook").setViewName("buyBook");
        registry.addViewController("/addBook").setViewName("addBook");
        registry.addViewController("/deleteBook").setViewName("deleteBook");
    }

}