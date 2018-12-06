package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.User;
import com.ytdsuda.management.repository.UserRepository;
import com.ytdsuda.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findUser(String userName) {
        return userRepository.findByUserNameIn(userName);
    }

    @Override
    public User updateInfo(User user) {
        return userRepository.save(user);
    }

}
