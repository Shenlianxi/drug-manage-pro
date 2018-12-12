package com.ytdsuda.management.exception;

import com.ytdsuda.management.enums.ResultEnum;

public class ResultException extends RuntimeException {
    private Integer code;
    public ResultException(ResultEnum resultEnum) {
//        super=>将返回的消息抛给父类中
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
