package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.User;
import com.ytdsuda.management.repository.UserRepository;
import com.ytdsuda.management.service.UserAuthorityService;
import com.ytdsuda.management.service.UserService;
import com.ytdsuda.management.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthorityService userAuthorityService;
    @Override
    public User save(User user) {
        String userName = user.getUserName();
        User result = userRepository.findByUserName(userName);
        if (ObjectUtils.isNotEmpty(result) == true) {
//            存在该字段
            return null;
        } else {
            User user1 = userRepository.save(user);
            return user1;
        }
    }

    @Override
    public List<User> findUser(String userName) {
        return userRepository.findByUserNameIn(userName);
    }

    @Override
    public User updateInfo(User user) {
        return userRepository.save(user);
    }

    @Override
    public Integer deleteUser(Integer userId) {
        Integer operCode = userAuthorityService.AuthorityCheck(userId, 1);
//        权限足够
        if (operCode == 1) {
            userRepository.deleteById(userId);
        } else if (operCode == 2) {
            System.out.println(userId + "无权限删除操作");
        } else {
            System.out.println("异常错误");
        }
        return operCode;
    }

}
