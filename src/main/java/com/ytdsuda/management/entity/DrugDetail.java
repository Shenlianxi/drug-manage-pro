package com.ytdsuda.management.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class DrugDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detailId;
//    药品id
    private String drugId;
//    药名
    private String drugName;
//    药品分类
    private String drugCategory;
//    药价
    private BigDecimal drugPrice;
//    厂商
    private String drugResource;
//    药品功能
    private String drugFunction;
//    药品图标
    private String drugIcon;
//    入库计数
    private Integer instockCount;
//    出库计数
    private Integer outstockCount;

    public DrugDetail() {
    }

    public DrugDetail(String drugId, String drugName, String drugCategory, BigDecimal drugPrice, String drugResource, String drugFunction, String drugIcon, Integer instockCount, Integer outstockCount) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.drugCategory = drugCategory;
        this.drugPrice = drugPrice;
        this.drugResource = drugResource;
        this.drugFunction = drugFunction;
        this.drugIcon = drugIcon;
        this.instockCount = instockCount;
        this.outstockCount = outstockCount;
    }
}
