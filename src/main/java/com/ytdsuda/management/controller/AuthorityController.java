package com.ytdsuda.management.controller;

import com.ytdsuda.management.VO.ResultVO;
import com.ytdsuda.management.dto.AuthorityDTO;
import com.ytdsuda.management.entity.Authority;
import com.ytdsuda.management.enums.AuthorityEnum;
import com.ytdsuda.management.service.impl.AuthorityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authority")
public class AuthorityController {
    @Autowired
    private AuthorityServiceImpl authorityService;

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

    @PostMapping("editAuth")
    public ResultVO editAuth(@RequestParam(value = "params") String params) {
        ResultVO resultVO = new ResultVO();
        System.out.println(params);
        return resultVO;
    }
}