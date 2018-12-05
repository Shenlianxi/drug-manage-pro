package com.ytdsuda.management.repository;

import com.ytdsuda.management.entity.RecentSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecentSummaryRepository extends JpaRepository<RecentSummary, Integer> {
    List<RecentSummary> findByType(Integer type);
//    保存测试方法
}
