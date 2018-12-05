package com.ytdsuda.management.controller;

import com.ytdsuda.management.VO.ResultVO;
import com.ytdsuda.management.entity.RecentSummary;
import com.ytdsuda.management.entity.TotalInfo;
import com.ytdsuda.management.repository.RecentSummaryRepository;
import com.ytdsuda.management.service.TotalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/show")
public class TotalGetinfoController {
    @Autowired
    private TotalInfoService totalInfoService;
    @Autowired
    private RecentSummaryRepository recentSummaryRepository;
    @GetMapping("totalinfo")
    public ResultVO getInfo() {
        ResultVO resultVO = new ResultVO();
        List<TotalInfo> result = totalInfoService.findAll();
        if (result.size() > 0) {
            resultVO.setSuccess(true);
            resultVO.setData(result);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("无数据");
        }
        return resultVO;
    }

    @GetMapping("mapdata")
    public ResultVO getMapData() {
        ResultVO resultVO = new ResultVO();
        List<RecentSummary> summaryList = recentSummaryRepository.findAll();
        System.out.println(summaryList);
        if (summaryList.size() > 0) {
            resultVO.setData(summaryList);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("查询错误");
        }
        return resultVO;
    }
}
