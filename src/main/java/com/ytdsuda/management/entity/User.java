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
//    昵称
    private String nickName;
//    部门
    private String position;
//    职位
    private String job;
//    age
    private Integer age;

    public User() {
    }

}
