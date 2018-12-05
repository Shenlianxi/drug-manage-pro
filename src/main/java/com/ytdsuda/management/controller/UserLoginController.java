package com.ytdsuda.management.controller;

import com.ytdsuda.management.VO.ResultVO;
import com.ytdsuda.management.entity.User;
import com.ytdsuda.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResultVO login(@RequestParam(value = "userName") String name, @RequestParam(value = "password") String password) {
        List<User> results = userService.findUser(name);
        ResultVO resultVO = new ResultVO();
        resultVO.setData(null);
        if (results.size() == 1) {
            User userResult = results.get(0);
            if (userResult.getUserPassword().equals(password)) {
                User resultData = new User();
                resultData.setUserName(userResult.getUserName());
                resultData.setUserRole(userResult.getUserRole());
                resultVO.setData(resultData);
                resultVO.setSuccess(true);
            } else {
                resultVO.setSuccess(false);
                resultVO.setErrorMsg("用户不存在");
            }
        }
//        resultVO.setData(Arrays.asList(results));
        return resultVO;
    }

}
