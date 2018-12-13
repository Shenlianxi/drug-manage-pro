package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.Authority;
import com.ytdsuda.management.repository.AuthorityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleAuthorityServiceImplTest {
    @Autowired
    RoleAuthorityServiceImpl roleAuthorityService;
    @Autowired
    AuthorityRepository authorityRepository;
    @Test
    public void findRoleAuth() {
        List<Authority> list = roleAuthorityService.findRoleAuth(3);
        System.out.println(list);
//        authorityRepository.findByName("数据中心");
    }

    @Test
    public void modify() {
        Integer roleId = 3;
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(11);
        list.add(12);
        roleAuthorityService.changeRoleAuth(roleId, list);
    }
}