package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.TotalInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TotalInfoServiceImplTest {
    @Autowired
    private TotalInfoServiceImpl totalInfoService;
    @Test
    public void findAll() {
        List<TotalInfo> result = totalInfoService.findAll();
        System.out.println(result);
    }

    @Test
    public void update() {
        TotalInfo totalInfo = totalInfoService.updateTotalInfo("instock", 10);
        System.out.println(totalInfo);
    }
}