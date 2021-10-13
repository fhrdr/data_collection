package com.example.data_collection.controller.back;

import com.example.data_collection.entity.Student;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StudentService;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PathVariable;
=======
>>>>>>> origin/master
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
<<<<<<< HEAD
import javax.websocket.server.PathParam;
=======
>>>>>>> origin/master
import java.awt.print.Pageable;

@RestController
@RequestMapping("/back/student")
public class StudentController {
<<<<<<< HEAD
    //注入
    @Resource
    private StudentService studentService;

    /**
     * 查询所有学生信息
     * @return
     */
=======
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
>>>>>>> origin/master
    @RequestMapping("/getAll")
    public ResponseResult getAllStudent(){
        return studentService.findAllStudent();
    }

<<<<<<< HEAD
    /**
     * 通过岗位查询学生信息
     * @param sDepartment
     * @return
     */
=======
>>>>>>> origin/master
    @RequestMapping("/getByDepartment")
    public ResponseResult getByDepartment(String sDepartment){
        return studentService.findStudentByDepartment(sDepartment);
    }

<<<<<<< HEAD
    /**
     * 添加学生信息
     * @param student
     * @return
     */
=======
>>>>>>> origin/master
    @RequestMapping("/add")
    public ResponseResult addStudent(Student student){
        return studentService.insertStudent(student);
    }

<<<<<<< HEAD
    /**
     * 修改学生信息
     * @param student
     * @return
     */
=======
>>>>>>> origin/master
    @RequestMapping("/update")
    public ResponseResult updateStudent(Student student){
        return studentService.updateStudent(student);
    }

<<<<<<< HEAD
    /**
     * 通过id删除学生信息
     * @param sId
     * @return
     */
    @RequestMapping("/delete")
    public ResponseResult deleteStudent(@PathParam("sId") Long sId){
        return studentService.deleteStudent(sId);
    }
=======
>>>>>>> origin/master

}
