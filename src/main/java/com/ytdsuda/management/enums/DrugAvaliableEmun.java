package com.ytdsuda.management.enums;

import lombok.Getter;

@Getter
public enum DrugAvaliableEmun {
    AVALIABLE(1, "药品可用"),
    UNAVALIABLE(0, "药品不可用")
    ;
    private Integer code;
    private String message;

    DrugAvaliableEmun(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
