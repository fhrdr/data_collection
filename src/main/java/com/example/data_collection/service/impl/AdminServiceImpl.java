package com.example.data_collection.service.impl;

import com.example.data_collection.dao.AdminDao;
import com.example.data_collection.entity.Admin;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    //注入
    @Autowired
    private AdminDao adminDao;

    /**
     * 查找所有管理人员信息
     */
    @Override
    public ResponseResult findAll() {
        return ResponseResult.SUCCESS("获取成功").setData(adminDao.findAll());
    }

    /*
    通过id查找管理员信息
     */
    @Override
    public ResponseResult findById(Long id) {
        return ResponseResult.SUCCESS("查找成功").setData(adminDao.findById(id).orElse(null));
    }

    /**
     * 添加管理员信息
     *
     * @param admin
     * @return
     */
    @Override
    public ResponseResult insertAdmin(Admin admin) {
        if (admin.getName() != null) {
            return ResponseResult.FAILED("该用户已经存在");
        }
        return ResponseResult.SUCCESS("添加成功").setData(adminDao.save(admin));


    }

    /**
     * 更新管理员信息
     * @param admin 管理员信息
     * @return
     */
    @Override
    public ResponseResult updateAdmin(Admin admin) {
        if (admin.getName()==null){
            return ResponseResult.FAILED("该用户不存在");
        }
        return ResponseResult.SUCCESS("修改成功").setData(adminDao.save(admin));
    }

    @Override
    public ResponseResult deleteAdminById(Long id) {
        return ResponseResult.SUCCESS("删除成功").setData(adminDao.deleteByid(id));
    }
}
