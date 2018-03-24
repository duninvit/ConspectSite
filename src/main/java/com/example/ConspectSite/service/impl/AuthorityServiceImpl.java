package com.example.ConspectSite.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.ConspectSite.model.Authority;
import com.example.ConspectSite.repository.AuthorityRepository;
import com.example.ConspectSite.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Authority> findById(Long id) {
        // TODO Auto-generated method stub

        Authority auth = this.authorityRepository.findById(id).orElse(authorityRepository.getOne(id));
        List<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }

    @Override
    public List<Authority> findByname(String name) {
        // TODO Auto-generated method stub
        Authority auth = this.authorityRepository.findByName(name);
        List<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }

}
