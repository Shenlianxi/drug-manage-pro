package com.ytdsuda.management.controller;

import com.ytdsuda.management.VO.ResultVO;
import com.ytdsuda.management.dto.AuthorityDTO;
import com.ytdsuda.management.dto.PrivilegeDTO;
import com.ytdsuda.management.entity.Authority;
import com.ytdsuda.management.entity.User;
import com.ytdsuda.management.entity.UserRole;
import com.ytdsuda.management.enums.AuthorityEnum;
import com.ytdsuda.management.repository.AuthorityRepository;
import com.ytdsuda.management.repository.UserRepository;
import com.ytdsuda.management.repository.UserRoleRepository;
import com.ytdsuda.management.service.RoleAuthorityService;
import com.ytdsuda.management.service.impl.AuthorityServiceImpl;
import com.ytdsuda.management.utils.FAST;
import com.ytdsuda.management.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authority")
public class AuthorityController {
    @Autowired
    private AuthorityServiceImpl authorityService;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private HttpSession session;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository repository;
//    获取全部权限
    @GetMapping("getAll")
    public ResultVO getAll() {
        ResultVO resultVO = new ResultVO();
        List<Authority> authorityList = authorityService.findAll();
        if (authorityList != null) {
            resultVO.setData(authorityList);
        } else {
            resultVO.setErrorMsg("查询异常");
            resultVO.setSuccess(false);
        }
        return resultVO;
    }
//    超级管理员可以新增权限
    @PostMapping("addNew")
    public ResultVO addNew(@RequestParam(value = "name") String name,
                           @RequestParam(value = "code") String code,
                           @RequestParam(value = "moudleName") String moudleName) {
        ResultVO resultVO = new ResultVO();
        AuthorityDTO authorityDTO = new AuthorityDTO(name, code, moudleName);
        Integer result = authorityService.save(authorityDTO);
        if (result == AuthorityEnum.SUCCESS.getCode()) {
            resultVO.setData(authorityDTO);
        } else {
            if (result == AuthorityEnum.QUERY_MOUDLE_FAIL.getCode()) {
                resultVO.setErrorMsg(AuthorityEnum.QUERY_MOUDLE_FAIL.getMessage());
            } else if (result == AuthorityEnum.UNIQUE_AUTHORITY.getCode()) {
                resultVO.setErrorMsg(AuthorityEnum.UNIQUE_AUTHORITY.getMessage());
            } else {
                resultVO.setErrorMsg(AuthorityEnum.UNDEFINED_ERROR.getMessage());
            }
            resultVO.setSuccess(false);
        }
        return resultVO;
    }
//编辑权限, 只有超级管理员才有
    @PostMapping("editAuth")
    public ResultVO editAuth(@RequestParam(value = "params") String params) {
        ResultVO resultVO = new ResultVO();
        System.out.println(params);
        Authority authority = FAST.parseObject(params, Authority.class);
        Authority result = authorityRepository.save(authority);
        if (result != null) {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("添加失败");
        } else {
            resultVO.setData(result);
        }
        return resultVO;
    }
// 获取该角色下的权限
    @GetMapping("getPriority")
    public ResultVO getPriority(@RequestParam(value = "roleId") Integer roleId) {
        ResultVO resultVO = new ResultVO();
        List<Authority> resultList = roleAuthorityService.findRoleAuth(roleId);
        if (resultList != null) {
            resultVO.setData(resultList);
//            Optional<UserRole> userRole = userRoleRepository.findById(roleId);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg(AuthorityEnum.UNDEFINED_ERROR.getMessage());
        }
        return resultVO;
    }
//    修改权限
    @PostMapping("modify")
    public ResultVO modify(@RequestParam(value = "roleId") Integer roleId,
                           @RequestParam(value = "authIds") List<Integer> authIds ) {
        ResultVO resultVO = new ResultVO();
        Integer code = roleAuthorityService.changeRoleAuth(roleId, authIds);
        if (code == AuthorityEnum.AUTHOR_AVALIABLE.getCode()) {
            resultVO.setData(authIds);
        } else {
            resultVO.setSuccess(false);
            if (code == AuthorityEnum.AUTHORITY_DENY.getCode()) {
                resultVO.setErrorMsg(AuthorityEnum.AUTHORITY_DENY.getMessage());
            } else {
                resultVO.setErrorMsg(AuthorityEnum.UNDEFINED_ERROR.getMessage());
            }
        }
        return resultVO;
    }
//    获取用户权限
    @GetMapping("userPrivlege")
    public ResultVO getUserPrivlege() {
        ResultVO resultVO = new ResultVO();
//        获取userId
        Integer currentUserId = Integer.parseInt(session.getAttribute("userId").toString());
//        找到user
        Optional<User> user = repository.findById(currentUserId);
        String roleName = user.get().getUserRole();
        UserRole result = userRoleRepository.findByRoleName(roleName);
        PrivilegeDTO privilegeDTO = new PrivilegeDTO();
        if (ObjectUtils.isNotEmpty(result)) {
//            resultVO.setData(result);
            privilegeDTO.setRole(result);
            List<Authority> resultList = roleAuthorityService.findRoleAuth(result.getRoleId());
            privilegeDTO.setCode(resultList);
            resultVO.setData(privilegeDTO);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("查询异常");
        }
        return resultVO;
    }
}
