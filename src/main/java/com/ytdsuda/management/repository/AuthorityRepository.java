package com.ytdsuda.management.repository;

import com.ytdsuda.management.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
//    根据权限名查询权限
    Authority findByName(String name);
}
