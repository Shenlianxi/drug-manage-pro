package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.User;
import com.ytdsuda.management.entity.UserRole;
import com.ytdsuda.management.enums.AuthorityEnum;
import com.ytdsuda.management.enums.ResultEnum;
import com.ytdsuda.management.repository.UserRepository;
import com.ytdsuda.management.repository.UserRoleRepository;
import com.ytdsuda.management.service.UserAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserAuthorityImpl implements UserAuthorityService {
    @Autowired
    private HttpSession session;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository roleRepository;
    /*
     * @params; 用户id 或者 角色id , 类型: 1, 删除用户等, 用户和用户之间, 2. 用户对于角色的操作
     * */
    @Override
    public Integer AuthorityCheck(Integer id, Integer type) {
//        Integer currentUserId = (Integer) session.getAttribute("userId");
        Integer currentUserId = 3;
        /*
         * 返回值1:可以操作, 2:权限不足, 其他1004异常错误
         * */
        User loginUser = userRepository.findByUserId(currentUserId);
        String currentRole = loginUser.getUserRole();
//        当前用户的等级权限
        Integer currentLevel = roleRepository.findByRoleName(currentRole).getRoleLevel();
        Integer operationLevel = null;
        if (type == 1) {
//        操作的id
            Integer opUserId = id;
            User operUser = userRepository.findByUserId(opUserId);
//        有这个用户id
            if (currentUserId != null && opUserId != null) {
                String opRole = operUser.getUserRole();
                operationLevel = roleRepository.findByRoleName(opRole).getRoleLevel();
            } else {
                return AuthorityEnum.UNDEFINED_ERROR.getCode();
            }
        } else {
//            Integer roleLevel = roleRepository.findById(id).get().getRoleLevel();
            Optional<UserRole> opRole = roleRepository.findById(id);
            if (opRole.isPresent()) {
                operationLevel = opRole.get().getRoleLevel();
            } else {
                return AuthorityEnum.UNDEFINED_ERROR.getCode();
            }
        }
//            权限通过
        if (operationLevel >= currentLevel) {
            System.out.println("通过");
            return AuthorityEnum.AUTHOR_AVALIABLE.getCode();
        } else {
//            权限拦截
            System.out.println("不通过");
            return AuthorityEnum.AUTHORITY_DENY.getCode();
        }
    }

}
