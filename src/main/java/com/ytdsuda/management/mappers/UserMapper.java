package com.ytdsuda.management.mappers;

import com.ytdsuda.management.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user")
    List<User> listAll();
}
