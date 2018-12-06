package com.ytdsuda.management.service;

import com.ytdsuda.management.entity.User;

import java.util.List;

public interface UserService {
//    添加用户
    User save(User user);
//查询用户是否存在(登录)
    List<User> findUser(String userName);
//    修改用户信息
    User updateInfo(User user);
}
