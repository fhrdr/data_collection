package com.example.data_collection.service;

import com.example.data_collection.entity.Student;
import com.example.data_collection.result.ResponseResult;

import javax.servlet.http.HttpServletRequest;

public interface StudentService {
    // 获取学生个人信息
    ResponseResult getStudentInfo(HttpServletRequest request);
    // 修改密码
    ResponseResult changePassword(String oldPassword, String newPassword, HttpServletRequest request);

    // 杨修伟部分
    //获取所有学生信息
    ResponseResult findAllStudent();
    //通过部门查询学生信息
    ResponseResult findStudentByDepartment(String sDepartment);
    //添加学生
    ResponseResult insertStudent(Student student);
    //修改学生信息
    ResponseResult updateStudent(Student student);
    //通过ID删除学生信息
    ResponseResult deleteStudent(Long sId);
}
