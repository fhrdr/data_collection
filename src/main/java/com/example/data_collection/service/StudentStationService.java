package com.example.data_collection.service;

import com.example.data_collection.entity.StudentStation;
import com.example.data_collection.entity.StudentStationInfo;
import com.example.data_collection.result.ResponseResult;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface StudentStationService {
    // 学生选择岗位
    ResponseResult studentChooseStation(Long stId , HttpSession session);
    // 学生取消岗位
    ResponseResult studentCancelStation(Long stId , HttpSession session);

    // 杨修伟部分
    /**
     * 添加信息
     * @param studentStation
     * @return
     */
    ResponseResult insertStudentStation(StudentStation studentStation);

    List<StudentStationInfo> findStudentinfo();
}
