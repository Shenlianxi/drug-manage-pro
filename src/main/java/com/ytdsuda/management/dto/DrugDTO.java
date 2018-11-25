package com.ytdsuda.management.dto;

import com.ytdsuda.management.enums.DrugAvaliableEmun;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DrugDTO {
    //    药品Id
    private String drugId;
    //    药名
    private String drugName;
    //    分类
    private String drugCategory;
    //    单价
    private BigDecimal drugPrice;
    //    库存
    private Integer drugAmount;
    //    厂商
    private String drugResource;
    //    药品功能
    private String drugFunction;
    //    药品图标
    private String drugIcon;

    public DrugDTO() {
    }

    public DrugDTO(String drugId, String drugName, String drugCategory, BigDecimal drugPrice, Integer drugAmount, String drugResource, String drugFunction, String drugIcon) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.drugCategory = drugCategory;
        this.drugPrice = drugPrice;
        this.drugAmount = drugAmount;
        this.drugResource = drugResource;
        this.drugFunction = drugFunction;
        this.drugIcon = drugIcon;
    }
}
