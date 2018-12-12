package com.ytdsuda.management.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    DRUG_NOT_EXSIT(1404, "商品不存在"),
    QUERY_SUCCESS(100, "查询成功"),
    QUERY_FAIL(101, "查询失败"),
    INSERT_SUCCESS(200, "插入信息成功"),
    INSERT_FAIL(201, "插入失败"),
    DELETE_SUCCESS(300, "删除成功"),
    DELETE_FAIL(301, "删除失败")
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
