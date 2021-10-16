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
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/back/admin")
public class AdminController {
    //注入
    @Autowired
    private AdminService adminService;

    /**
     * 查询所有管理员信息
     * @return
     */
    @RequestMapping("/findAll")
    public ResponseResult getAllAdmin(){
        return adminService.findAll();
    }

    /**
     * 通过id查询管理员
     * @param id 用户ID
     * @return
     */
    @RequestMapping("/findById/{id}")
    public ResponseResult getAdminById(@PathVariable("id") Long id){
        return adminService.findById(id);
    }

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @RequestMapping("/addAdmin")
    public ResponseResult addAdmin(Admin admin){
        return adminService.insertAdmin(admin);
    }

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    @RequestMapping("/updateAdmin")
    public ResponseResult updateAdmin(Admin admin){
        return adminService.updateAdmin(admin);
    }

    /**
     * 通过id删除管理员
     * @param id
     * @return
     */
    @RequestMapping("/deleteAdmin")
    public ResponseResult deleteAdminById(@PathParam("id") Long id){
        return adminService.deleteAdminById(id);
    }
}
