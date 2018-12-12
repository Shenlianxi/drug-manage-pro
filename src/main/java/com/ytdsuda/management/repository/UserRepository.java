package com.ytdsuda.management.repository;

import com.ytdsuda.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
//    根据username查询结果
    List<User> findByUserNameIn(String userName);

    User findByUserName(String userName);

    User findByUserId(Integer userId);

}
