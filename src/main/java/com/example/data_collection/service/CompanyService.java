package com.example.data_collection.service;

import com.example.data_collection.entity.Company;
import com.example.data_collection.result.ResponseResult;

public interface CompanyService {
    //根据公司名查询公司信息
    ResponseResult findByCname(String cName);
    //添加公司信息
    ResponseResult addCompany(Company company);
    //修改公司信息
    ResponseResult updateCompany(Company company);
    //通过ID删除公司信息
    ResponseResult deleteStudent(Long cId);
}
