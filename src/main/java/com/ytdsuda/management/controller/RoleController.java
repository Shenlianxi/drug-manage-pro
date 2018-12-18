package com.ytdsuda.management.controller;

import com.ytdsuda.management.VO.ResultVO;
import com.ytdsuda.management.entity.UserRole;
import com.ytdsuda.management.enums.AuthorityEnum;
import com.ytdsuda.management.enums.ResultEnum;
import com.ytdsuda.management.repository.UserRoleRepository;
import com.ytdsuda.management.service.impl.UserRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private UserRoleServiceImpl userRoleService;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @PostMapping("add")
    public ResultVO add(@PathParam(value = "roleName") String roleName,
                        @PathParam(value = "roleDesc") String roleDesc) {
        ResultVO resultVO = new ResultVO();
        UserRole userRole = new UserRole();
        userRole.setRoleName(roleName);
        userRole.setRoleDesc(roleDesc);
        Integer state = userRoleService.save(userRole);
        if (state == ResultEnum.INSERT_SUCCESS.getCode()) {
            resultVO.setData(userRole);
        } else {
            resultVO.setSuccess(false);
            if (state == ResultEnum.UNIQUE_RESULT.getCode()) {
                resultVO.setErrorMsg(ResultEnum.UNIQUE_RESULT.getMessage());
            } else {
                resultVO.setErrorMsg(ResultEnum.INSERT_FAIL.getMessage());
            }
        }
        return resultVO;
    }

    @GetMapping("getList")
    public ResultVO getList() {
        ResultVO resultVO = new ResultVO();
        List<UserRole> userRoles = userRoleRepository.findAll();
        if (userRoles != null) {
            resultVO.setData(userRoles);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("获取列表信息失败");
        }
        return resultVO;
    }

    @PostMapping("updateRole")
    public ResultVO update(@RequestParam(value = "roleId") Integer roleId,
                           @RequestParam(value = "roleName") String roleName,
                           @RequestParam(value = "roleDesc") String roleDesc) {
        ResultVO resultVO = new ResultVO();
        UserRole userRole = userRoleRepository.findById(roleId).get();
        userRole.setRoleName(roleName);
        userRole.setRoleDesc(roleDesc);
        Integer code = userRoleService.updateRole(userRole);
        if (code == 1) {
            resultVO.setData(userRole);
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
}
