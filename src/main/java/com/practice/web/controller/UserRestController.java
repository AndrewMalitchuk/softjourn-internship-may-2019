package com.practice.web.controller;

import com.practice.web.model.Model;
import com.practice.web.model.Role;
import com.practice.web.model.User;
import com.practice.web.repository.BookRepository;
import com.practice.web.repository.RoleRepository;
import com.practice.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping(path = "/api/user")
@Validated
public class UserRestController
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(path="/", produces = "application/json")
    public Model index(){
        return  new Model("user_test_success");
    }

    @GetMapping(path = "/datetime",produces = "application/json")
    public  Model time(){
        return new Model(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
    }

    @GetMapping(path = "/allUsers",produces = "application/json")
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/allRoles",produces = "application/json")
    public Iterable<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @GetMapping(path = "/userByRole",produces = "application/json")
    public Iterable<User> getUserByRole(@Min(4) @Param("role") String role){
        return userRepository.getUserByRole(role);
    }

}