package com.ytdsuda.management.repository;

import com.ytdsuda.management.entity.DrugDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugDetailRepository extends JpaRepository<DrugDetail, Integer> {
    DrugDetail findByDrugId(String drugId);
//    删除数据,通过主键的list
    List<DrugDetail> deleteByDetailIdIn(List<Integer> detailIds);
//    查询所有的id对应的明细
    List<DrugDetail> findByDrugIdIn(List<String> drugIds);
}
