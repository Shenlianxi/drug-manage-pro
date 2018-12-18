package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.Authority;
import com.ytdsuda.management.entity.RoleAuthority;
import com.ytdsuda.management.enums.AuthorityEnum;
import com.ytdsuda.management.repository.AuthorityRepository;
import com.ytdsuda.management.repository.RoleAuthorityRepository;
import com.ytdsuda.management.service.RoleAuthorityService;
import com.ytdsuda.management.service.UserAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService {
    @Autowired
    private RoleAuthorityRepository roleAuthorityRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private UserAuthorityService authorityService;

    @Override
    public List<Authority> findRoleAuth(Integer roleId) {
//        获取权限和角色
//        List<Integer> roleAuthorityList = roleAuthorityRepository.findAuthIdsByAuthorityId(roleId);
        List<RoleAuthority> lists = roleAuthorityRepository.findByRoleId(roleId);
        List<Integer> roleAuthorityList = new ArrayList<>();
        lists.forEach(el -> {
            roleAuthorityList.add(el.getAuthorityId());
        });
//        获取所有权限
        List<Authority> authorityList = authorityRepository.findAll();
        List<Authority> resultList = new ArrayList<>();
        authorityList.forEach(item -> {
            if (roleAuthorityList.contains(item.getId())) {
                resultList.add(item);
            }
        });
        System.out.println(resultList);
        return resultList;
    }

    @Override
    public Integer changeRoleAuth(Integer roleId, List<Integer> authIds) {
//        roleAuthorityRepository.deleteByRoleId(roleId);
        Integer authorityCode = authorityService.AuthorityCheck(roleId, 2);
        if (authorityCode == 1) {
//            权限pass
            List<RoleAuthority> roleAuthorityList = roleAuthorityRepository.findByRoleId(roleId);
            roleAuthorityRepository.deleteAll(roleAuthorityList);
            List<RoleAuthority> roleAuthorities = new ArrayList<>();
            authIds.forEach(ele -> {
                RoleAuthority temp = new RoleAuthority(roleId, ele);
                roleAuthorities.add(temp);
            });
            List<RoleAuthority> list = roleAuthorityRepository.saveAll(roleAuthorities);
            if (list != null) {
                return AuthorityEnum.AUTHOR_AVALIABLE.getCode();
            } else {
                return AuthorityEnum.UNDEFINED_ERROR.getCode();
            }
        } else {
            if (authorityCode == AuthorityEnum.AUTHORITY_DENY.getCode()) {
                return 2;
            } else {
                return AuthorityEnum.UNDEFINED_ERROR.getCode();
            }
        }
    }


}
