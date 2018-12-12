package com.ytdsuda.management.enums;

import lombok.Getter;

@Getter
public enum AuthorityEnum {
    AUTHOR_AVALIABLE(1, "权限可以操作"),
    AUTHORITY_DENY(2, "权限不足"),
    UNDEFINED_ERROR(1004, "异常错误")
    ;

    private Integer code;
    private String message;

    AuthorityEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
