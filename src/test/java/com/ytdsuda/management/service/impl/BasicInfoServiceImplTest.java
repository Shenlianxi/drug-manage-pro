package com.ytdsuda.management.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicInfoServiceImplTest {

    @Autowired
    private BasicInfoServiceImpl basicInfoService;
    @Test
    public void getCurrentUser() {
        Integer integer = basicInfoService.getCurrentUser();
        System.out.println(integer);
    }

    @Test
    public void updateStatus() {
        Integer integer = basicInfoService.updateStatus(92);
    }
}