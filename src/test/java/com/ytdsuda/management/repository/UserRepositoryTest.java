package com.ytdsuda.management.repository;

import com.ytdsuda.management.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testSave() {
        User user = new User("superAdmin", "123456", "超级管理员");
        repository.save(user);
    }
    @Test
    public void findByUserNameIn() {
        List<User> userList = repository.findByUserNameIn("admin");
        System.out.println(userList);
    }
}