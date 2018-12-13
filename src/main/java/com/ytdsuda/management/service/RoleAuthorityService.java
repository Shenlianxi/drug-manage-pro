package com.ytdsuda.management.service;

import com.ytdsuda.management.entity.Authority;

import java.util.List;

public interface RoleAuthorityService {
    List<Authority> findRoleAuth(Integer roleId);
    List<Integer> changeRoleAuth(Integer roleId, List<Integer> authIds);
}
