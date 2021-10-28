package com.example.data_collection.controller.back;

import com.example.data_collection.entity.Admin;
import com.example.data_collection.interceptor.CheckTooFrequentCommit;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
@PreAuthorize("@permission.admin()")
@RequestMapping("/back")
public class AdminController {
    //注入
    @Autowired
    private AdminService adminService;

    /**
     * 查询所有管理员信息
     * @return 返回结果
     */
    @GetMapping("/findAllAdmin")
    public ResponseResult getAllAdmin(){
        return adminService.findAllAdmin();
    }

    /**
     * 通过id查询管理员
     * @param id 用户ID
     * @return 返回结果
     */
    @PostMapping("/findAdminById/{id}")
    public ResponseResult getAdminById(@PathVariable("id") Long id){
        return adminService.findAdminById(id);
    }

    /**
     * 添加管理员
     * @param admin admin用户信息
     * @return 返回结果
     */
    @PostMapping("/addAdmin")
    public ResponseResult addAdmin(Admin admin){
        return adminService.addAdmin(admin);
    }

    /**
     * 修改管理员信息
     * @param admin admin用户信息
     * @return 返回结果
     */
    @PostMapping("/editAdmin")
    public ResponseResult editAdmin(Admin admin){
        return adminService.editAdmin(admin);
    }

    /**
     * 通过id删除管理员
     * @param id 管理员ID
     * @return 返回结果
     */
    @DeleteMapping("/deleteAdminById")
    public ResponseResult deleteAdminById(@PathParam("id") Long id){
        return adminService.deleteAdminById(id);
    }
}
