package com.practice.web.service;

import com.practice.web.model.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
    public User findUserById_user(Integer id_user);


}
