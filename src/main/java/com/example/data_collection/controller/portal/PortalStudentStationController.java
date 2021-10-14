package com.example.data_collection.controller.portal;

import com.example.data_collection.dao.StudentStationDao;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StudentStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@PreAuthorize("@permission.use()")
@RequestMapping("/portal")
public class PortalStudentStationController {
    // 注入
    @Autowired
    private StudentStationService studentStationService;

    /**
     * 学生选择岗位
     * @param stId 岗位ID
     * @return 返回结果
     */
    @GetMapping("/chooseStation/{stId}")
    ResponseResult chooseStation(@PathVariable("stId") Long stId , HttpServletRequest request){
        return studentStationService.studentChooseStation(stId , request);
    }

    /**
     * 学生取消岗位
     * @param stId 岗位ID
     * @return 返回结果
     */
    @GetMapping("/cancelStation/{stId}")
    ResponseResult cancelStation(@PathVariable("stId") Long stId , HttpServletRequest request){
        return studentStationService.studentCancelStation(stId , request);
    }
}
