package com.ytdsuda.management.dto;

import com.ytdsuda.management.entity.Authority;
import lombok.Data;

import java.util.List;

@Data
public class PrivilegeDTO {
//    用户角色对象
    private Object role;
//    所有权限
    private List<Authority> code;
}
