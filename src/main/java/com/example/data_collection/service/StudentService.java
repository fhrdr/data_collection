package com.example.data_collection.service;

import com.example.data_collection.entity.Student;
import com.example.data_collection.result.ResponseResult;

import javax.servlet.http.HttpServletRequest;

public interface StudentService {
    // 获取学生个人信息
    ResponseResult getStudentInfo(HttpServletRequest request);

    // 杨修伟部分
    ResponseResult findAllStudent();

    ResponseResult findStudentByDepartment(String sDepartment);

    ResponseResult insertStudent(Student student);

    ResponseResult updateStudent(Student student);
}
