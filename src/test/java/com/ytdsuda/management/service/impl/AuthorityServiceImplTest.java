package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.dto.AuthorityDTO;
import com.ytdsuda.management.entity.Authority;
import com.ytdsuda.management.repository.AuthorityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityServiceImplTest {
    @Autowired
    AuthorityServiceImpl authorityService;
    @Autowired
    AuthorityRepository repository;
    @Test
    public void save() {
        AuthorityDTO authorityDTO = new AuthorityDTO("销售系统", "saleSystem", "导航栏");
        authorityService.save(authorityDTO);
//        Authority authority = repository.findByName("订单管理");
//        System.out.println(authority);
    }

    @Test
    public void findAll() {
        List<Authority> list = authorityService.findAll();
        System.out.println(list);
    }
}