package com.ytdsuda.management.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class TotalInfo {
    @Id
    private Integer totalId;
//    总入库
    private Integer totalIncome;
//    总出库
    private Integer totalOutstock;
//    总操作
    private Integer totalOperation;
//    总销售额
    private Integer totalSales;

    public TotalInfo() {
    }

    public TotalInfo(Integer totalIncome, Integer totalOutstock, Integer totalOperation, Integer totalSales) {
        this.totalIncome = totalIncome;
        this.totalOutstock = totalOutstock;
        this.totalOperation = totalOperation;
        this.totalSales = totalSales;
    }
}
