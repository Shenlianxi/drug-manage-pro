package com.ytdsuda.management.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@DynamicUpdate
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
//    角色名
    private String roleName;
//    描述
    private String roleDesc;
//    创建者
    private String creator;
//    权限大小
    private Integer roleLevel;

    public UserRole() {
    }
}
