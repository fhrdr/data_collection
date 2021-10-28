package com.example.data_collection.controller.back;

import com.example.data_collection.entity.StudentStation;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StudentStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@PreAuthorize("@permission.admin()")
@RequestMapping("/back")
public class StudentStationController {
    //注入
    @Autowired
    private StudentStationService studentStationService;

    /**
     * 添加学生-岗位信息
     * @param studentStation 学生岗位信息
     * @return 返回结果
     */
    @RequestMapping("/addStudentStation")
    public ResponseResult addStudentStation(StudentStation studentStation){
        return studentStationService.addStudentStation(studentStation);
    }

    /**
     * 查询学生部门，姓名，班级，岗位，公司，选择时间信息
     * @return 返回结果
     */
    @RequestMapping("/findStudentInfo")
    public ResponseResult getStudentInfo(){
        return studentStationService.findStudentInfo();
    }

    /**
     * 通过班级查询关联信息
     * @param sClass 班级名称
     * @return 返回结果
     */
    @RequestMapping("/findByClass")
    public ResponseResult getByClass(@PathParam("sClass") String sClass){
        return studentStationService.findBySClass(sClass);
    }
}
