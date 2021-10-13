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
import java.util.List;

@RestController
@RequestMapping("/back/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @RequestMapping("/findAll")
    public ResponseResult getAllAdmin(){
        return adminService.findAll();
    }

    @RequestMapping("/findById/{id}")
    public ResponseResult getAdminById(@PathVariable("id") Long id){
        return adminService.findById(id);
    }

    @RequestMapping("/addAdmin")
    public ResponseResult addAdmin(Admin admin){
        return adminService.insertAdmin(admin);
    }

    @RequestMapping("/updateAdmin")
    public ResponseResult updateAdmin(Admin admin){
        return adminService.updateAdmin(admin);
    }

//    @RequestMapping("/deleteAdmin/{id}")
//    public ResponseResult deleteAdminById(@PathVariable("id") Long id){
//        return adminService.deleteAdminById(id);
//    }
}
