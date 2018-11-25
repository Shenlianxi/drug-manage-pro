package com.ytdsuda.management.VO;

import lombok.Data;

/*
http最外层对象
* */
@Data
public class ResultVO<T> {
//    错误码
    private Boolean success = true;
//    提示信息
    private String errorMsg = null;
    /*data,返回的具体内容
     data是一个对象, 可以定义一个范型*/
    private T data = null;
}
