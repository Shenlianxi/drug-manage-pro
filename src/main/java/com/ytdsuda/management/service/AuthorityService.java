package com.ytdsuda.management.service;

import com.ytdsuda.management.entity.Authority;

import java.util.List;

public interface AuthorityService {
//    超级管理员新增权限
    Authority save(Authority authority);
//    查询所有的权限
    List<Authority> findAll();
}
