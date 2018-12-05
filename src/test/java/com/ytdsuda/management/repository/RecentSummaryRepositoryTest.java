package com.ytdsuda.management.repository;

import com.ytdsuda.management.entity.RecentSummary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecentSummaryRepositoryTest {

    @Autowired RecentSummaryRepository repository;
    @Test
    public void findByType() {
        List<RecentSummary> list = repository.findByType(1);
        System.out.println(list);
    }
}