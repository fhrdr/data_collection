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

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/portal")
public class PortalStudentStationController {
    // 注入
    @Autowired
    private StudentStationService studentStationService;

    /**
     * 学生选择岗位
     * @param stId 岗位ID
     * @param session session
     * @return 返回结果
     */
    @PreAuthorize("@permission.use()")
    @GetMapping("/chooseStation/{stId}")
    ResponseResult chooseStation(@PathVariable("stId") Long stId , HttpSession session){
        return studentStationService.studentChooseStation(stId , session);
    }

    /**
     * 学生取消岗位
     * @param stId 岗位ID
     * @param session session
     * @return 返回结果
     */
    @PreAuthorize("@permission.use()")
    @GetMapping("/cancelStation/{stId}")
    ResponseResult cancelStation(@PathVariable("stId") Long stId , HttpSession session){
        return studentStationService.studentCancelStation(stId , session);
    }
}
