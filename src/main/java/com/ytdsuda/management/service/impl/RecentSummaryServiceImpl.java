package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.RecentSummary;
import com.ytdsuda.management.repository.RecentSummaryRepository;
import com.ytdsuda.management.service.RecentSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecentSummaryServiceImpl implements RecentSummaryService {
    @Autowired
    private RecentSummaryRepository recentSummaryRepository;
    @Override
    public RecentSummary save(RecentSummary recentSummary) {
        return recentSummaryRepository.save(recentSummary);
    }
}
