package com.ytdsuda.management.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ytdsuda.management.VO.ResultVO;
import com.ytdsuda.management.utils.JavaWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
    private static List<String> NO_LOGIN_URI = new ArrayList<String>();

    static{
        NO_LOGIN_URI.add("/dms/user/login");
    }

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        log.info("---------------------开始进入请求地址拦截----------------------------");
        String uri = request.getRequestURI();
        //无需拦截URI
        if(NO_LOGIN_URI.contains(uri)){
            return true;
        }
        /*获取用户传过来的token值*/
        HttpSession session = request.getSession();
        String token = request.getHeader("token");
        String userId = request.getHeader("userId");
        if (userId != null && userId != "undefined") {
            session.setAttribute("userId", userId);
        }
        if(session == null || session.getAttribute("userId") == null ){
            //验证不通过，返回错误信息
            ResultVO result = new ResultVO();
            result.setSuccess(false);
            result.setErrorMsg("未登录，请往登录页登录。");
            result.setLogin(false);
            response.setContentType("application/json; charset=utf-8");
            JSONObject json = (JSONObject) JSONObject.toJSON(result);
            response.getWriter().write(json.toJSONString());
            return false;
        }
        //验证不通过，返回错误信息
        Map<String, Object> res = JavaWebToken.parserJavaWebToken(token);
        if(res == null){
            ResultVO result = new ResultVO();
            result.setSuccess(false);
            result.setErrorMsg("Token解析错误 或者 过期");
            result.setLogin(false);
            response.setContentType("application/json; charset=utf-8");
            JSONObject json = (JSONObject) JSONObject.toJSON(result);
            response.getWriter().write(json.toJSONString());
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("---------------视图渲染之后的操作-------------------------0");
    }
}
