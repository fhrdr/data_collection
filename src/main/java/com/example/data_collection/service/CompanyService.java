package com.example.data_collection.service;

import com.example.data_collection.entity.Company;
import com.example.data_collection.result.ResponseResult;
import sun.rmi.runtime.Log;

import java.util.List;

public interface CompanyService {
    // 查询所有公司信息
    ResponseResult findAllCompany(int page, int size);
    // 根据公司名查询公司信息
    ResponseResult findCompanyByName(int page, int size, String name);
    // 添加公司信息
    ResponseResult addCompany(Company company);
    // 批量添加公司
    ResponseResult addCompanies(List<Company> companies);
    // 修改公司信息
    ResponseResult editCompany(Company company);
    // 通过ID删除公司信息
    ResponseResult deleteCompanyById(Long id);
    // 批量删除公司
    ResponseResult deleteCompanies(List<Long> ids);

    // 搜索公司名字
    ResponseResult getAllCompanyName();
}
