package com.ytdsuda.management.entity;

import com.ytdsuda.management.enums.DrugAvaliableEmun;
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
public class DrugMaster {
    @Id
    private String drugId;
//    药名
    private String drugName;
//    分类
    private String drugCategory;
//    单价
    private BigDecimal drugPrice;
//    库存
    private Integer drugAmount;
//    可用性
    private Integer drugAvaliable = DrugAvaliableEmun.AVALIABLE.getCode();
////  创建时间
//    private Date createTime;
////    更新时间
//    private Date updateTime;

    public DrugMaster() {
    }

    public DrugMaster(String drugName, String drugCategory, BigDecimal drugPrice, Integer drugAmount, Integer drugAvaliable) {
        this.drugName = drugName;
        this.drugCategory = drugCategory;
        this.drugPrice = drugPrice;
        this.drugAmount = drugAmount;
        this.drugAvaliable = drugAvaliable;
    }
}
