package com.ytdsuda.management.repository;

import com.ytdsuda.management.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
//    找到角色对象
    UserRole findByRoleName(String roleName);
}
