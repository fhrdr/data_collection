package com.example.data_collection.controller.back;

import com.example.data_collection.entity.StudentStation;
import com.example.data_collection.entity.StudentStationInfo;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StudentStationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/back/ss")
public class StudentStationController {
    //注入
    @Resource
    private StudentStationService studentStationService;

    /**
     * 添加学生-岗位信息
     * @param studentStation
     * @return
     */
    @RequestMapping("/add")
    public ResponseResult addStudentStation(StudentStation studentStation){
        return studentStationService.insertStudentStation(studentStation);
    }


    /**
     * 查询学生部门，姓名，班级，岗位，公司，选择时间信息
     * @return
     */
    @RequestMapping("/getStudent")
    public ResponseResult getStudentinfo(){

        return ResponseResult.SUCCESS().setData(studentStationService.findStudentinfo());
    }



}
