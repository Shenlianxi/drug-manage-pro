package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.User;
import com.ytdsuda.management.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void save() {
//        User user = new User("testAdmin", "123456", "测试员");
//        User result = userService.save(user);
//        Assert.assertNotNull(result);
    }

    @Test
    public void findUser() {
        List<User> result = userService.findUser("admin");
        if (result.size() == 1) {
            System.out.println(result);
        } else {
            System.out.println("查询用户失败!");
        }
    }
}