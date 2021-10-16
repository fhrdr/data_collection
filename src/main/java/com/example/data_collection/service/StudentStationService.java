package com.example.data_collection.service;

import com.example.data_collection.entity.StudentStation;
import com.example.data_collection.result.ResponseResult;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface StudentStationService {
    // 学生选择岗位
    ResponseResult studentChooseStation(Long stId , HttpServletRequest request);
    // 学生取消岗位
    ResponseResult studentCancelStation(Long stId , HttpServletRequest request);

    // 杨修伟部分
    /**
     * 添加信息
     * @param studentStation
     * @return
     */
    ResponseResult insertStudentStation(StudentStation studentStation);
    //  查询学生姓名，部门，班级，公司，岗位，选择时间信息
    ResponseResult findStudentInfo();
    //通过班级查询学生，岗位，公司的相关信息
    ResponseResult findBySClass(String sClass);
}
