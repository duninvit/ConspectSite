package com.example.ConspectSite.service;

import java.util.List;

import com.example.ConspectSite.model.User;
import com.example.ConspectSite.model.UserRequest;

public interface UserService {
    void resetCredentials();

    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();

    User save(UserRequest user);
}
