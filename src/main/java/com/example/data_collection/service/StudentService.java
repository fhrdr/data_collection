package com.example.data_collection.service;

import com.example.data_collection.entity.Student;
import com.example.data_collection.result.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StudentService {
    /*
    前端部分
     */
    // 获取学生个人信息
    ResponseResult getStudentInfo(HttpServletRequest request);
    // 修改密码
    ResponseResult changePassword(String oldPassword, String newPassword, HttpServletRequest request);

    /*
    后端部分
     */
    //获取所有学生信息
    ResponseResult findAllStudent(int size, int page);
    // 查询所有班级
    ResponseResult findAllClass();
    // 根据班级查询所有学生信息
    ResponseResult findAllStudentsByClass(String className, int size, int page);
    // 查询所有学生信息
    ResponseResult findAllStudents();

    // 通过部门查询学生信息
    ResponseResult findStudentByDepartment(String sDepartment);
    // 添加学生
    ResponseResult addStudent(Student student);
    // 批量添加学生
    ResponseResult addStudents(List<Student> students);
    // 修改学生信息
    ResponseResult editStudent(Student student);
    // 通过ID删除学生信息
    ResponseResult deleteStudent(Long sId);
    // 批量删除学生
    ResponseResult deleteStudents(List<Long> ids);
}
