package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.UserRole;
import com.ytdsuda.management.enums.ResultEnum;
import com.ytdsuda.management.exception.ResultException;
import com.ytdsuda.management.repository.UserRoleRepository;
import com.ytdsuda.management.service.UserAuthorityService;
import com.ytdsuda.management.service.UserRoleService;
import com.ytdsuda.management.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserAuthorityService authorityService;
    @Override
    public Integer save(UserRole userRole) {
        UserRole role = userRoleRepository.save(userRole);
        if (ObjectUtils.isNotEmpty(role) == true) {
            return ResultEnum.INSERT_SUCCESS.getCode();
        } else {
//            throw new ResultException(ResultEnum.INSERT_FAIL);
            return ResultEnum.INSERT_FAIL.getCode();
        }
    }

    @Override
    public Integer updateRole(UserRole userRole) {
        Integer authorityCode = authorityService.AuthorityCheck(userRole.getRoleId(), 2);
        if (authorityCode == 1) {
            userRoleRepository.save(userRole);
        } else if (authorityCode == 2) {
            System.out.println("权限拦截");
        } else {
            System.out.println("操作异常");
        }
        return authorityCode;
    }

    @Override
    public Integer deleteRole(Integer roleId) {
        Integer authorityCode = authorityService.AuthorityCheck(roleId, 2);
        if (authorityCode == 1) {
            userRoleRepository.deleteById(roleId);
        } else if (authorityCode == 2) {
            System.out.println("权限拦截");
        } else {
            System.out.println("操作异常");
        }
        return authorityCode;
    }
}
