package com.example.data_collection.controller.back;

import com.example.data_collection.entity.Student;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StudentService;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.awt.print.Pageable;

@RestController
@RequestMapping("/back/student")
public class StudentController {
    //注入
    @Resource
    private StudentService studentService;

    /**
     * 查询所有学生信息
     * @return
     */
    @RequestMapping("/getAll")
    public ResponseResult getAllStudent(){
        return studentService.findAllStudent();
    }

    /**
     * 通过岗位查询学生信息
     * @param sDepartment
     * @return
     */
    @RequestMapping("/getByDepartment")
    public ResponseResult getByDepartment(String sDepartment){
        return studentService.findStudentByDepartment(sDepartment);
    }

    /**
     * 添加学生信息
     * @param student
     * @return
     */
    @RequestMapping("/add")
    public ResponseResult addStudent(Student student){
        return studentService.insertStudent(student);
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    @RequestMapping("/update")
    public ResponseResult updateStudent(Student student){
        return studentService.updateStudent(student);
    }

    /**
     * 通过id删除学生信息
     * @param sId
     * @return
     */
    @RequestMapping("/delete")
    public ResponseResult deleteStudent(@PathParam("sId") Long sId){
        return studentService.deleteStudent(sId);
    }

}
