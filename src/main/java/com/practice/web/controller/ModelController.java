package com.practice.web.controller;

import com.practice.web.model.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 * This class-controller was created 'as is' - just for testing correct of works of RESTful
 */
@RestController
public class ModelController {


    @RequestMapping("/about")
    public Model about(@RequestParam(value = "content", defaultValue = "OK") String content) {
        return new Model(content);
    }

    //for testing security for admin rest endpoint
    @RequestMapping("/api/admin")
    public Model adminTest(){
        return new Model("admin_test");
    }

    //for testing security for admin rest endpoint
    @RequestMapping("/api/user")
    public Model userTest(){
        return new Model("user_test");
    }

    @RequestMapping("/")
    public String root(){
        return "Everything works fine.";
    }

}
