package com.practice.web.controller;

import com.practice.web.model.Model;
import com.practice.web.model.Role;
import com.practice.web.model.User;
import com.practice.web.repository.RoleRepository;
import com.practice.web.repository.UserRepository;
import com.practice.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/user")
@Validated
public class UserRestController
{
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(path = "/datetime", produces = "application/json")
    public Model time() {
        return new Model(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
    }

    @GetMapping(path = "/allUsers", produces = "application/json")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/userByFilter", produces = "application/json")
    public Iterable<User> getUserByName(@Param("name") String name, @Param("surname") String surname,
                                        @Param("email") String email) {
        return userRepository.getUserByName(name, surname, email);
    }

    @GetMapping(path = "/disableUser", produces = "application/json")
    public void disableUserById_user(@Param("id_user") Integer id_user) {
        userRepository.disableUserById_user(id_user);
    }

    @GetMapping(path = "/allRoles", produces = "application/json")
    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping(path = "/userByRole", produces = "application/json")
    public Iterable<User> getUserByRole(@Size(min = 4, max = 5) @Param("role") String role) {
        return userRepository.getUserByRole(role);
    }


    @DeleteMapping(path = "/deleteUser/{id}")
    public void deleteUserById(@PathVariable Long id) {
        roleRepository.deleteById(id);
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/search")
    public ModelAndView searchUser() {
        ModelAndView mv = new ModelAndView("searchUser");
        mv.addObject("users", userRepository.findAll());
        return mv;
    }
    @RequestMapping(path = "/search/goSearch", method = RequestMethod.POST)
    public ModelAndView searchUser(@RequestParam("name") String name,
                                   @RequestParam("surname") String surname,
                                   @RequestParam("email") String email) {
        ModelAndView mv = new ModelAndView("searchUser");
        mv.addObject("users", userRepository.getUserByName(name, surname, email));
        return mv;
    }


    @RequestMapping(value = "/userEdit/{id}", method = RequestMethod.GET)
    public ModelAndView displayEditUserForm(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();
        User user = userService.findUserById_user(id);
        mv.addObject("roless",roleRepository.findAll());
        mv.addObject("user", user);
        mv.setViewName("/userEdit");
        return mv;
    }
//
//    @GetMapping("userEdit/{user}")
//    public String userEdit(@PathVariable User user, org.springframework.ui.Model model) {
//        model.addAttribute("user",user);
//        return "userEdit";
//    }

    @RequestMapping(value = "/userEdit/save", method = RequestMethod.POST)
    public ModelAndView saveEditedUser(
                                       @RequestParam String name,
                                       @RequestParam String surname,
                                       @RequestParam String sex,
                                       @RequestParam String address,
                                       @RequestParam String phone,
                                       @RequestParam Date date_of_birth,
                                       @RequestParam String email,
                                       @RequestParam String password,
                                       @RequestParam Map<String, String> form,
                                       @RequestParam("id_user") User user) {
        user.getRoles().clear();
        user.setName(name);
        System.out.println(name);
        user.setSurname(surname);
        user.setSex(sex);
        user.setAddress(address);
        user.setPhone(phone);
        user.setDate_of_birth(date_of_birth);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        HashSet<Role> userRole = new HashSet<>();

        Iterable<Role> rolesIt = roleRepository.findAll();
        Set<String> roles = new HashSet<>();
        for (Role role:
             rolesIt) {
            roles.add(role.getRole());
        }

        for (String key: form.keySet()
             ) {
            if (roles.contains(key)) {
                System.out.println("-----------------------------");
                System.out.println(key);
                System.out.println("------------------");
                userRole.add(roleRepository.findByRole(key));
            }

        }
        user.setRoles(userRole);

        ModelAndView mv = new ModelAndView("redirect:/api/user/search");
//
//        if (result.hasErrors()) {
//            System.out.println(result.toString());
//            return new ModelAndView("error");
//        }
//        userService.saveUser(user);
        userRepository.save(user);
        return mv;
    }

}