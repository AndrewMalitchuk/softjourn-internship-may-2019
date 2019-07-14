package com.practice.web.controller;

import com.practice.web.model.Model;
import com.practice.web.model.Role;
import com.practice.web.model.User;
import com.practice.web.repository.RoleRepository;
import com.practice.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping(path = "/api/user")
public class UserRestController
{

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Qualifier("roleRepository")
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

    @GetMapping(path = "/userByFilter",produces = "application/json")
    public Iterable<User> getUserByName(@Param("name") String name, @Param("surname") String surname,
                                        @Param("email") String email){
        return userRepository.getUserByName(name, surname, email);
    }

    @GetMapping(path = "/disableUser",produces = "application/json")
    public void disableUserById_user(@Param("id_user") Integer id_user) {
        userRepository.disableUserById_user(id_user);
    }

    @GetMapping(path = "/allRoles",produces = "application/json")
    public Iterable<Role> getAllRoles(){
        return roleRepository.findAll();
    }




}