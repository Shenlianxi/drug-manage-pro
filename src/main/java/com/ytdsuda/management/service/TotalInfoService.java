package com.ytdsuda.management.service;

import com.ytdsuda.management.entity.TotalInfo;

import java.util.List;

public interface TotalInfoService {
    List<TotalInfo> findAll();
    TotalInfo updateTotalInfo(String type, Integer count);
}
