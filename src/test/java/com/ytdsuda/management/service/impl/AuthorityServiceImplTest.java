package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.Authority;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityServiceImplTest {
    @Autowired
    AuthorityServiceImpl authorityService;
    @Test
    public void save() {
        Authority authority = new Authority("导航栏", "nav",
                -1, "测测", "test");
        authorityService.save(authority);
    }

    @Test
    public void findAll() {
    }
}