package com.ytdsuda.management.service;

import com.ytdsuda.management.entity.UserRole;

public interface UserRoleService {
//    新建角色
    Integer save(UserRole userRole);
//    更新角色信息
    Integer updateRole(UserRole userRole);
//    删除角色信息
    Integer deleteRole(Integer roleId);
}
