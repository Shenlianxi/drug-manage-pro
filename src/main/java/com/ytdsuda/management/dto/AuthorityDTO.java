package com.ytdsuda.management.dto;

import lombok.Data;

@Data
public class AuthorityDTO {
//    权限名
    private String name;
//    code
    private String code;
//    依赖模块名
    private String  moudleName;

    public AuthorityDTO() {
    }

    public AuthorityDTO(String name, String code, String moudleName) {
        this.name = name;
        this.code = code;
        this.moudleName = moudleName;
    }
}
