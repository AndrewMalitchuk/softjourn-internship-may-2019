package com.practice.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_USER")
@RequestMapping(path = "/user")
public class UserController {

    @RequestMapping(path="/")
    public String index(){
        return "/user";
    }

}
