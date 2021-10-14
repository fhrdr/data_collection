package com.example.data_collection.service;

import com.example.data_collection.result.ResponseResult;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface LoginService {
    // 学生登录
    ResponseResult studentLogin(String number , String password);

    // 管理员登录
    ResponseResult adminLogin(String number , String password);

    // 注销登录
    ResponseResult LoginOut(HttpServletRequest request);
}
