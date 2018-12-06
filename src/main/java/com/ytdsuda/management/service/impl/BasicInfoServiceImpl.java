package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.BasicInfo;
import com.ytdsuda.management.repository.BasicInfoRepository;
import com.ytdsuda.management.service.BasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasicInfoServiceImpl implements BasicInfoService {
    @Autowired
    private BasicInfoRepository basicInfoRepository;
    @Override
    public Integer getCurrentUser() {
        Optional<BasicInfo> basicInfo = basicInfoRepository.findById(2018);
        BasicInfo result = basicInfo.get();
        return result.getUserId();
    }

    @Override
    public Integer updateStatus(Integer userId) {
        Optional<BasicInfo> tempInfo = basicInfoRepository.findById(2018);
        BasicInfo result = tempInfo.get();
        result.setUserId(userId);
        result.setLoginTimes(result.getLoginTimes() + 1);
        BasicInfo basicInfo = basicInfoRepository.save(result);
        return basicInfo.getLoginTimes();
    }
}
