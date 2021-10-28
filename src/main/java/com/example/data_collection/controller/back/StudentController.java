package com.example.data_collection.controller.back;

import com.example.data_collection.entity.Student;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@PreAuthorize("@permission.admin()")
@RequestMapping("/back")
public class StudentController {
    //注入
    @Autowired
    private StudentService studentService;

    /**
     * 查询所有学生信息
     * @return 返回所有学生信息
     */
    @RequestMapping("/findAllStudents/{size}/{page}")
    public ResponseResult getAllStudent(@PathVariable("size") int size,
                                        @PathVariable("page") int page){
        return studentService.findAllStudent(size, page);
    }

    /**
     * 获取所有班级
     * @return 返回结果
     */
    @GetMapping("/findAllClass")
    ResponseResult findAllClass(){
        return studentService.findAllClass();
    }

    /**
     * 通过学生班级获取学生
     * @param size 数量
     * @param page 页码
     * @param className 班级名字
     * @return 返回结果
     */
    @GetMapping("/findStudentsByClass/{size}/{page}/{className}")
    ResponseResult findStudentsByClass(@PathVariable("size") int size,
                                       @PathVariable("page") int page,
                                       @PathVariable("className") String className){
        return studentService.findAllStudentsByClass(className, size, page);
    }

    /**
     * 查询所有学生的基础信息
     * @return 返回结果
     */
    @GetMapping("/findStudents")
    ResponseResult findStudentsByClass(){
        return studentService.findAllStudents();
    }


    /**
     * 通过部门查询学生信息
     * @param sDepartment 部门
     * @return 返回结果
     */
    @RequestMapping("/findByDepartment")
    public ResponseResult getByDepartment(String sDepartment){
        return studentService.findStudentByDepartment(sDepartment);
    }

    /**
     * 添加学生信息
     * @param student 学生信息
     * @return 返回结果
     */
    @RequestMapping("/addStudent")
    public ResponseResult addStudent(Student student){
        return studentService.addStudent(student);
    }

    /**
     * 修改学生信息
     * @param student 学生信息
     * @return 返回结果
     */
    @RequestMapping("/editStudent")
    public ResponseResult editStudent(Student student){
        return studentService.editStudent(student);
    }

    /**
     * 通过id删除学生信息
     * @param sId 学生ID
     * @return 返回结果
     */
    @DeleteMapping("/deleteStudent/{sId}")
    public ResponseResult deleteStudent(@PathVariable("sId") Long sId){
        return studentService.deleteStudent(sId);
    }

    /**
     * 批量添加学生
     * @param students 学生列表
     * @return 返回结果
     */
    @PostMapping("/addStudents")
    public ResponseResult addStudents(List<Student> students){
        return studentService.addStudents(students);
    }

    /**
     * 批量删除学生
     * @param ids 学生id列表
     * @return 返回结果
     */
    @DeleteMapping("/deleteStudents")
    public ResponseResult deleteStudents(List<Long> ids){
        return studentService.deleteStudents(ids);
    }
}
