package com.example.data_collection.service;

import com.example.data_collection.entity.Admin;
import com.example.data_collection.result.ResponseResult;

public interface AdminService {
    // 查询所有的管理员
    ResponseResult findAllAdmin();
    // 通过ID查询管理员
    ResponseResult findAdminById(Long id);
    // 添加管理员
    ResponseResult addAdmin(Admin admin);
    // 修改管理员信息
    ResponseResult editAdmin(Admin admin);
    // 通过ID删除管理员
    ResponseResult deleteAdminById(Long id);
}
