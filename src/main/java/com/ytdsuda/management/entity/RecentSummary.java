package com.ytdsuda.management.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@DynamicUpdate
public class RecentSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer infoId;
//    类型1.入库地区分布 2.出库... 3. 销售金额分布
    private Integer type;

    private String area;

    private Integer count;

    private BigDecimal money;

    public RecentSummary() {
    }

    public RecentSummary(Integer type, String area, Integer count, BigDecimal money) {
        this.type = type;
        this.area = area;
        this.count = count;
        this.money = money;
    }
}
