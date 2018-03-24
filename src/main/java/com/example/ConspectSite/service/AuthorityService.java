package com.example.ConspectSite.service;

import com.example.ConspectSite.model.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> findById(Long id);

    List<Authority> findByname(String name);

}
