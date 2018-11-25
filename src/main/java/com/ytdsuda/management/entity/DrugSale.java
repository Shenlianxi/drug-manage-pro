package com.ytdsuda.management.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class DrugSale {
    @Id
    private Integer saleId;
    private String drugId;
    private String drugName;
//    销售额
    private BigDecimal saleVolume;

    public DrugSale() {
    }

    public DrugSale(Integer saleId, String drugId, String drugName, BigDecimal saleVolume) {
        this.saleId = saleId;
        this.drugId = drugId;
        this.drugName = drugName;
        this.saleVolume = saleVolume;
    }
}
