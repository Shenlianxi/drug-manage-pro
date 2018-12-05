package com.ytdsuda.management.controller;

import com.ytdsuda.management.VO.ResultVO;
import com.ytdsuda.management.entity.RecentSummary;
import com.ytdsuda.management.repository.RecentSummaryRepository;
import com.ytdsuda.management.service.impl.RecentSummaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestDataController {
    @Autowired
    private RecentSummaryServiceImpl summaryRepository;
    @PostMapping("insert")
    public ResultVO insert(@RequestParam(value = "type") Integer type,
                           @RequestParam(value = "area") String area,
                           @RequestParam(value = "count", required = false) Integer count,
                           @RequestParam(value = "money", required = false) Float money) {
        ResultVO resultVO = new ResultVO();
        RecentSummary recentSummary = new RecentSummary();
        recentSummary.setType(type);
        recentSummary.setArea(area);
        if (count != null) {
            recentSummary.setCount(count);
        }
        if (money != null) {
            recentSummary.setMoney(new BigDecimal(money));
        }
        summaryRepository.save(recentSummary);
        return resultVO;
    }
}
