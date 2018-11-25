package com.ytdsuda.management.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {
//    用户id,主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
//用户名
    private String userName;
//    密码
    private String userPassword;
//    角色
    private String userRole;

    public User() {
    }

    public User(String userName, String userPassword, String userRole) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }
}
