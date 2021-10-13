package com.example.data_collection.controller.portal;

import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/portal")
public class StudentController {
    // 注入
    @Autowired
    private StudentService studentService;

    /**
     * 获取学生基本信息
     * @param session session
     * @return 返回结果
     */
    @PreAuthorize("@permission.use()")
    @GetMapping("/getStudentInfo")
    ResponseResult getStudentInfo(HttpSession session){
        return studentService.getStudentInfo(session);
    }
}
