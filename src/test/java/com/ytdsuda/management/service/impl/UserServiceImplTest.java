package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.User;
import com.ytdsuda.management.service.UserAuthorityService;
import com.ytdsuda.management.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthorityService userAuthorityService;

    @Test
    public void save() {
        User user = new User();
        user.setUserName("admin111");
        user.setUserPassword("1231231213");
        user.setUserRole("测试管理员");
        User result = userService.save(user);
        System.out.println(result);
    }

    @Test
    public void findUser() {
        Integer result = userAuthorityService.AuthorityCheck(1, 2);
        System.out.println(result);
//        List<User> result = userService.findUser("admin");
//        if (result.size() == 1) {
//            System.out.println(result);
//        } else {
//            System.out.println("查询用户失败!");
//        }
    }
}