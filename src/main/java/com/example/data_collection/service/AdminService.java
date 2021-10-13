package com.example.data_collection.service;

import com.example.data_collection.entity.Admin;
import com.example.data_collection.result.ResponseResult;

public interface AdminService {
    /**
     *查找所有管理员信息
     */
    ResponseResult findAll();

    /**
     * 通过id查找管理员信息
     * @param id
     * @return
     */
    ResponseResult findById(Long id);

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    ResponseResult insertAdmin(Admin admin);


    /**
     * 修改管理员信息
     * @param admin 管理员信息
     */
    ResponseResult updateAdmin(Admin admin);

    /**
     * 删除管理员信息
     * @param id
     * @return
     */

    ResponseResult deleteAdminById(Long id);
}
