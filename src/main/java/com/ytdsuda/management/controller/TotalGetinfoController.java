package com.ytdsuda.management.controller;

import com.ytdsuda.management.VO.ResultVO;
import com.ytdsuda.management.entity.TotalInfo;
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
}
