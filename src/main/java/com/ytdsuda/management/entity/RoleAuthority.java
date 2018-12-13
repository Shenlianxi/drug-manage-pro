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
public class RoleAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uaId;
//    用户id
    private Integer roleId;
//    权限id
    private Integer authorityId;

    public RoleAuthority() {
    }

    public RoleAuthority(Integer roleId, Integer authorityId) {
        this.roleId = roleId;
        this.authorityId = authorityId;
    }
}
