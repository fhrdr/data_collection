package com.example.data_collection.controller.back;

import com.example.data_collection.entity.Admin;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
<<<<<<< HEAD
import javax.websocket.server.PathParam;
=======
>>>>>>> origin/master
import java.util.List;

@RestController
@RequestMapping("/back/admin")
public class AdminController {
<<<<<<< HEAD
    //注入
    @Resource
    private AdminService adminService;

    /**
     * 查询所有管理员信息
     * @return
     */
=======

    @Resource
    private AdminService adminService;

>>>>>>> origin/master
    @RequestMapping("/findAll")
    public ResponseResult getAllAdmin(){
        return adminService.findAll();
    }

<<<<<<< HEAD
    /**
     * 通过id查询管理员
     * @param id
     * @return
     */
=======
>>>>>>> origin/master
    @RequestMapping("/findById/{id}")
    public ResponseResult getAdminById(@PathVariable("id") Long id){
        return adminService.findById(id);
    }

<<<<<<< HEAD
    /**
     * 添加管理员
     * @param admin
     * @return
     */
=======
>>>>>>> origin/master
    @RequestMapping("/addAdmin")
    public ResponseResult addAdmin(Admin admin){
        return adminService.insertAdmin(admin);
    }

<<<<<<< HEAD
    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
=======
>>>>>>> origin/master
    @RequestMapping("/updateAdmin")
    public ResponseResult updateAdmin(Admin admin){
        return adminService.updateAdmin(admin);
    }

<<<<<<< HEAD
    /**
     * 通过id删除管理员
     * @param id
     * @return
     */
    @RequestMapping("/deleteAdmin")
    public ResponseResult deleteAdminById(@PathParam("id") Long id){
        return adminService.deleteAdminById(id);
    }
=======
//    @RequestMapping("/deleteAdmin/{id}")
//    public ResponseResult deleteAdminById(@PathVariable("id") Long id){
//        return adminService.deleteAdminById(id);
//    }
>>>>>>> origin/master
}
