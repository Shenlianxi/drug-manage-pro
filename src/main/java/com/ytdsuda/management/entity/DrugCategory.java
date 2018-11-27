package com.ytdsuda.management.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class DrugCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
//    类名
    private String categoryName;
//描述
    private String categoryDesc;
    public DrugCategory() {
    }

    public DrugCategory(String categoryName, String categoryDesc) {
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }
}
