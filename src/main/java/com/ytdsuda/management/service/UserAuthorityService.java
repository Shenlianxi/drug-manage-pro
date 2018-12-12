package com.ytdsuda.management.service;

public interface UserAuthorityService {
    /*
    * @params; 用户id 或者 角色id , 类型: 1, 删除用户等, 用户和用户之间, 2. 角色和角色之间
    * */
    Integer AuthorityCheck(Integer id, Integer type);
}
