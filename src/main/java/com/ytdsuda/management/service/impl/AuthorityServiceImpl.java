package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.dto.AuthorityDTO;
import com.ytdsuda.management.entity.Authority;
import com.ytdsuda.management.enums.AuthorityEnum;
import com.ytdsuda.management.repository.AuthorityRepository;
import com.ytdsuda.management.service.AuthorityService;
import com.ytdsuda.management.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    public Integer save(AuthorityDTO authorityDTO) {
        String parentName = authorityDTO.getMoudleName();
        Authority authority = authorityRepository.findByName(parentName);
//        依赖权限存在
        if (ObjectUtils.isNotEmpty(authority) == true) {
            String saveName = authorityDTO.getName();
            Authority saveResult = authorityRepository.findByName(saveName);
            if (ObjectUtils.isNotNull(saveResult) == true) {
                System.out.println("插入的权限已经存在");
                return AuthorityEnum.UNIQUE_AUTHORITY.getCode();
            } else {
                Authority insertData = new Authority(authorityDTO.getName(), authorityDTO.getCode(), authority.getId(),
                        authority.getName(), authority.getCode());
                Authority result = authorityRepository.save(insertData);
                if (ObjectUtils.isNotEmpty(result) == true) {
                    return AuthorityEnum.SUCCESS.getCode();
                } else {
                    return AuthorityEnum.UNDEFINED_ERROR.getCode();
                }
            }
        } else {
            return AuthorityEnum.QUERY_MOUDLE_FAIL.getCode();
        }
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }
}
