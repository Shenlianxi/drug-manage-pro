package com.ytdsuda.management.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    DRUG_NOT_EXSIT(1404, "商品不存在")
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
