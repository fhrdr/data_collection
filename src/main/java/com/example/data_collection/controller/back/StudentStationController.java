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
    @Resource
    private StudentStationService studentStationService;

    @RequestMapping("/add")
    public ResponseResult addStudentStation(StudentStation studentStation){
        return studentStationService.insertStudentStation(studentStation);
    }



    @RequestMapping("/getStudent")
    public ResponseResult getStudentinfo(){

        return ResponseResult.SUCCESS().setData(studentStationService.findStudentinfo());
    }


}
