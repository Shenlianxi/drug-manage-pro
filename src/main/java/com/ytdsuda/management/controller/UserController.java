package com.ytdsuda.management.controller;

import com.alibaba.fastjson.JSONObject;
import com.ytdsuda.management.VO.ResultVO;
import com.ytdsuda.management.dto.UserDTO;
import com.ytdsuda.management.entity.User;
import com.ytdsuda.management.enums.AuthorityEnum;
import com.ytdsuda.management.enums.ResultEnum;
import com.ytdsuda.management.repository.UserRepository;
import com.ytdsuda.management.service.UserService;
import com.ytdsuda.management.service.impl.BasicInfoServiceImpl;
import com.ytdsuda.management.utils.JavaWebToken;
import com.ytdsuda.management.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BasicInfoServiceImpl basicInfoService;
    @Autowired
    private HttpSession session;
    @PostMapping("login")
    public ResultVO login(@RequestParam(value = "userName") String name,
                          @RequestParam(value = "password") String password) {
        List<User> results = userService.findUser(name);
        ResultVO resultVO = new ResultVO();
        resultVO.setData(null);
        if (results.size() == 1) {
            User userResult = results.get(0);
            basicInfoService.updateStatus(userResult.getUserId());
            if (userResult.getUserPassword().equals(password)) {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(userResult, userDTO);
                String token = JavaWebToken
                        .createJavaWebToken((JSONObject)JSONObject.toJSON(userResult),
                        30L * 24L * 3600L * 1000L);
                if (token != null) {
                    userDTO.setToken(token);
                }
                resultVO.setData(userDTO);
            } else {
                resultVO.setSuccess(false);
                resultVO.setLogin(false);
                resultVO.setErrorMsg("用户名密码错");
            }
        }
//        resultVO.setData(Arrays.asList(results));
        return resultVO;
    }

    @GetMapping("getUser")
    public ResultVO getUser() {
        ResultVO resultVO = new ResultVO();
        Integer currentUserId = basicInfoService.getCurrentUser();
        Optional<User> user = userRepository.findById(currentUserId);
        if (user.isPresent()) {
            User userInfo = user.get();
            userInfo.setUserPassword(null);
            Boolean userExsist = ObjectUtils.isNotNull(userInfo) && ObjectUtils.isNotEmpty(userInfo);
            if (userExsist) {
                resultVO.setData(userInfo);
            } else {
                resultVO.setErrorMsg("查询用户失败");
                resultVO.setSuccess(false);
            }
        }
        return resultVO;
    }

    @GetMapping("allUser")
    public ResultVO getAll() {
        ResultVO resultVO = new ResultVO();
        List<User> list =  userRepository.findAll();
        if (list != null) {
            list.forEach(el -> {
                el.setUserPassword(null);
            });
            resultVO.setData(list);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg(ResultEnum.QUERY_FAIL.getMessage());
        }
        return resultVO;
    }

    @PostMapping("saveProfile")
    public ResultVO getUser(@RequestParam(value = "nickName", required = false) String nickName,
                            @RequestParam(value = "position", required = false) String position,
                            @RequestParam(value = "job", required = false) String job,
                            @RequestParam(value = "age", required = false) Integer age) {
        ResultVO resultVO = new ResultVO();
        Integer userId = basicInfoService.getCurrentUser();
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();
        user.setUserId(userId);
        user.setNickName(nickName);
        user.setPosition(position);
        user.setJob(job);
        user.setAge(age);
        User result = userRepository.save(user);
        Boolean userExsist = ObjectUtils.isNotNull(result) && ObjectUtils.isNotEmpty(result);
        if (userExsist) {
            resultVO.setData("id:" + userId);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("录入失败");
        }
        return resultVO;
    }
    @PostMapping("changePassword")
    public ResultVO changePwd(@RequestParam(value = "oldPwd") String oldPwd,
                              @RequestParam(value = "newPwd") String newPwd) {
        ResultVO resultVO = new ResultVO();
        Integer currentUserId = basicInfoService.getCurrentUser();
        Optional<User> user = userRepository.findById(currentUserId);
        if (user.isPresent()) {
            User userInfo = user.get();
            if (oldPwd.equals(userInfo.getUserPassword())) {
                userInfo.setUserPassword(newPwd);
                userRepository.save(userInfo);
                resultVO.setData(userInfo.getUserId());
            } else {
                resultVO.setSuccess(false);
                resultVO.setErrorMsg("原密码输入有误");
            }
        } else {
            resultVO.setSuccess(false);
            resultVO.setData("检索用户失败");
        }
        return resultVO;
    }

    @DeleteMapping("delUser/{userId}")
    public ResultVO delUser(@PathVariable(value = "userId") Integer userId) {
        ResultVO resultVO = new ResultVO();
        Integer state = userService.deleteUser(userId);
        if (state == AuthorityEnum.AUTHOR_AVALIABLE.getCode()) {
            resultVO.setData(userId);
        } else {
            resultVO.setSuccess(false);
            if (state == AuthorityEnum.UNDEFINED_ERROR.getCode()) {
                resultVO.setErrorMsg(AuthorityEnum.UNDEFINED_ERROR.getMessage());
            } else {
                resultVO.setErrorMsg(AuthorityEnum.AUTHORITY_DENY.getMessage());
            }
        }
        return resultVO;
    }

    @PostMapping("add")
    public ResultVO addUser(@RequestParam(value = "userName") String userName,
                            @RequestParam(value = "password") String password,
                            @RequestParam(value = "userRole") String userRole) {
        ResultVO resultVO = new ResultVO();
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(password);
        user.setUserRole(userRole);
        User result =  userService.save(user);
        if (result != null) {
            resultVO.setData(result);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("该用户已存在,请重新输入.");
        }
        return resultVO;
    }

}
