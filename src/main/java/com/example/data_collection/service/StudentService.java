package com.example.data_collection.service;

import com.example.data_collection.result.ResponseResult;

import javax.servlet.http.HttpSession;

public interface StudentService {
    // 获取学生个人信息
    ResponseResult getStudentInfo(HttpSession session);
}
