package com.example.data_collection.service;

import com.example.data_collection.entity.StudentStation;
import com.example.data_collection.result.ResponseResult;

import javax.servlet.http.HttpSession;

public interface StudentStationService {
    // 学生选择岗位
    ResponseResult studentChooseStation(Long stId , HttpSession session);
    // 学生取消岗位
    ResponseResult studentCancelStation(Long stId , HttpSession session);

    // 杨修伟部分
    //添加学生-岗位 信息
    ResponseResult insertStudentStation(StudentStation studentStation);
    //  查询学生姓名，部门，班级，公司，岗位，选择时间信息
    ResponseResult findStudentinfo();
    //通过班级查询学生，岗位，公司的相关信息
    ResponseResult findBySclass(String sClass);
}
