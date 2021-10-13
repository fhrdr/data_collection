package com.example.data_collection.service;

import com.example.data_collection.result.ResponseResult;

import javax.servlet.http.HttpSession;

public interface StudentStationService {
    // 学生选择岗位
    ResponseResult studentChooseStation(Long stId , HttpSession session);
    // 学生取消岗位
    ResponseResult studentCancelStation(Long stId , HttpSession session);
}
