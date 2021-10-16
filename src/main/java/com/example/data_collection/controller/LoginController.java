package com.example.data_collection.controller;

import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    // 注入
    @Autowired
    private LoginService loginService;

    /**
     * 管理员登录
     * @param name 管理员账号
     * @param password 管理员密码
     * @return 返回登录结果
     */
    @GetMapping("/admin")
    ResponseResult admin(String name , String password, HttpServletRequest request){
        return loginService.adminLogin(name , password, request);
    }

    /**
     * 学生登录
     * @param number 学生账号
     * @param password 学生密码
     * @return 返回登录结果
     */
    @GetMapping("/login")
    ResponseResult login(String number , String password, HttpServletRequest request){
        return loginService.studentLogin(number , password, request);
    }

    /**
     * 注销登录
     * @return 返回结果
     */
    @GetMapping("/loginOut")
    ResponseResult loginOut(HttpServletRequest request){
        return loginService.LoginOut(request);
    }

    /**
     * 处理 403 错误
     * @return 返回结果
     */
    @GetMapping("/error403")
    ResponseResult error(){
        return ResponseResult.FAILED("登录超时，请重新登录");
    }
}
