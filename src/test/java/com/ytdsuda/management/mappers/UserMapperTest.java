package com.ytdsuda.management.mappers;

import com.ytdsuda.management.entity.TotalInfo;
import com.ytdsuda.management.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void listAll() {
        List<User> userList = userMapper.listAll();
        userList.forEach(System.out::println);
    }
}