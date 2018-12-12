package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.Authority;
import com.ytdsuda.management.repository.AuthorityRepository;
import com.ytdsuda.management.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }
}
