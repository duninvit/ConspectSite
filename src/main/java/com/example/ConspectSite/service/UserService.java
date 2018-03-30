package com.example.ConspectSite.service;

import com.example.ConspectSite.model.User;
import com.example.ConspectSite.model.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
