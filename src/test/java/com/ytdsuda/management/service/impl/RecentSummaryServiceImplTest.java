package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.RecentSummary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecentSummaryServiceImplTest {
    @Autowired
    private RecentSummaryServiceImpl recentSummaryService;
    @Test
    public void save() {
        RecentSummary recentSummary = new RecentSummary(1, "北京", 1999, new BigDecimal(99.98));
        recentSummaryService.save(recentSummary);
    }
}