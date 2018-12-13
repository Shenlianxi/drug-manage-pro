package com.ytdsuda.management.repository;

import com.ytdsuda.management.entity.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, Integer> {
//    根据roleId删除
//    void deleteByRoleId(Integer roleId);
//    查询权限
    List<RoleAuthority> findByRoleId(Integer roleId);
//    @Query(value = "select authorityId from roleAuthority table where table.roleId =? 1")
//    List<Integer> findAuthIdsByAuthorityId(Integer roleId);
//    @Modifying
//    @Transactional
//    @Query(value = "delete from roleAuthority es where es.roleId = ?1")
//    Integer deleteByRoleId(Integer roleId);
    }
