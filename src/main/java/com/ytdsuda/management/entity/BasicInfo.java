package com.ytdsuda.management.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class BasicInfo {
    @Id
    private Integer basicId;
//    当前用户Id
    private Integer userId;
//    登录次数
    private Integer loginTimes;

    public BasicInfo() {
    }
}
