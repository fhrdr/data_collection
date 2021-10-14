package com.example.data_collection.controller.portal;

import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@PreAuthorize("@permission.use()")
@RequestMapping("/portal")
public class PortalStudentController {
    // 注入
    @Autowired
    private StudentService studentService;

    /**
     * 获取学生基本信息
     * @return 返回结果
     */
    @GetMapping("/getStudentInfo")
    ResponseResult getStudentInfo(HttpServletRequest request){
        return studentService.getStudentInfo(request);
    }

    /**
     * 学生修改密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param request request
     * @return 返回结果
     */
    @GetMapping("/changePassword")
    ResponseResult changePassword(String oldPassword, String newPassword, HttpServletRequest request){
        return studentService.changePassword(oldPassword, newPassword, request);
    }
}
