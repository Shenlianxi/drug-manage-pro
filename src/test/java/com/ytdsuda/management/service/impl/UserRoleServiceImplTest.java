package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.UserRole;
import com.ytdsuda.management.service.UserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleServiceImplTest {
@Autowired
private UserRoleService userRoleService;
    @Test
    public void save() {
    }

    @Test
    public void updateRole() {
        UserRole role = new UserRole();
        role.setRoleId(1);
        role.setRoleName("322131测试测试11");
        role.setRoleDesc("专门测试的");
        role.setCreator("普通管理员122233332");
        role.setRoleLevel(1);
        userRoleService.deleteRole(role.getRoleId());
    }

    @Test
    public void deleteRole() {
    }
}