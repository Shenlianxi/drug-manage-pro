package com.ytdsuda.management.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer userId;
    private String userName;
    private String userRole;
    private String nickName;
    private String token;

    public UserDTO() {
    }
}
