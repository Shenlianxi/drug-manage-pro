package com.ytdsuda.management.repository;

import com.ytdsuda.management.entity.DrugMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DrugMasterRepository extends JpaRepository<DrugMaster, String> {
    Optional<DrugMaster> findByDrugName(String drugName);
    DrugMaster findByDrugId(String drugId);

//    @Query(value="select drugName from DrugMaster u where u.name=?1")
//    List<String> find(String userName);
}
