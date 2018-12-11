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
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    权限名
    private String name;
//    code
    private String code;
//    模块id
    private Integer moudleId;
//    模块name
    private String moudleName;
    private String moudleCode;

    public Authority() {
    }

    public Authority(String name, String code, Integer moudleId, String moudleName, String moudleCode) {
        this.name = name;
        this.code = code;
        this.moudleId = moudleId;
        this.moudleName = moudleName;
        this.moudleCode = moudleCode;
    }
}
