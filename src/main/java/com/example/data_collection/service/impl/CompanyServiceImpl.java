package com.example.data_collection.service.impl;

import com.example.data_collection.dao.CompanyDao;
import com.example.data_collection.entity.Company;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CompanyServiceImpl implements CompanyService {
    //注入
    @Resource
    private CompanyDao companyDao;

    /**
     * 通过公司名查询公司信息
     * @param cName
     * @return
     */
    @Override
    public ResponseResult findByCname(String cName) {
        return ResponseResult.SUCCESS("查询成功").setData(companyDao.findByCName(cName));
    }

    /**
     * 添加公司信息
     * @param company
     * @return
     */
    @Override
    public ResponseResult addCompany(Company company) {
        return ResponseResult.SUCCESS("添加成功").setData(companyDao.save(company));
    }

    /**
     * 修改公司信息
     * @param company
     * @return
     */
    @Override
    public ResponseResult updateCompany(Company company) {
        return ResponseResult.SUCCESS("修改成功").setData(companyDao.save(company));
    }

    /**
     * 通过id删除公司信息
     * @param cId
     * @return
     */
    @Override
    public ResponseResult deleteStudent(Long cId) {
        return ResponseResult.SUCCESS("删除成功").setData(companyDao.deleteByid(cId));
    }
}
