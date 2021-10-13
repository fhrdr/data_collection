package com.example.data_collection.controller.back;

import com.example.data_collection.entity.Student;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StudentService;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.awt.print.Pageable;

@RestController
@RequestMapping("/back/student")
public class StudentController {
    @Resource
    private StudentService studentService;

//    @RequestMapping("/getAll")
//    public ResponseResult getAllStudent(Integer pageNo,Integer size){
//        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"s_id");
//        Sort sort = new Sort(order);
//        Pageable pageable = new PageRequest(pageNo,size,sort);
//
//        return studentService.findAll(pageable);
//    }
    @RequestMapping("/getAll")
    public ResponseResult getAllStudent(){
        return studentService.findAllStudent();
    }

    @RequestMapping("/getByDepartment")
    public ResponseResult getByDepartment(String sDepartment){
        return studentService.findStudentByDepartment(sDepartment);
    }

    @RequestMapping("/add")
    public ResponseResult addStudent(Student student){
        return studentService.insertStudent(student);
    }

    @RequestMapping("/update")
    public ResponseResult updateStudent(Student student){
        return studentService.updateStudent(student);
    }


}
