package com.example.data_collection.service.impl;

import com.example.data_collection.dao.CompanyDao;
import com.example.data_collection.entity.Company;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.CompanyService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    //注入
    @Resource
    private CompanyDao companyDao;

    /**
     * 查询所有的公司的信息
     * @param page 页码
     * @param size 数量
     * @return 返回结果
     */
    @Override
    public ResponseResult findAllCompany(int page, int size) {
        // 归正page和size
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0){
            size = 20;
        }
        page -=1;
        // 设置分页
        Pageable pageable = PageRequest.of(page , size);
        // 查询所有信息
        return ResponseResult.SUCCESS("查询公司成功").setData(companyDao.findAll(pageable));
    }

    /**
     * 通过公司名查询公司信息
     * @param companyName 公司名
     * @return 返回结果
     */
    @Override
    public ResponseResult findCompanyByName(int page, int size, String companyName) {
        // 归正page和size
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0){
            size = 20;
        }
        page-=1;
        // 设置分页
        Pageable pageable = PageRequest.of(page , size);
        // 查询所有的信息
        return ResponseResult.SUCCESS("查询公司成功").setData(companyDao.findAll(new Specification<Company>() {
            @Override
            public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("cName") , companyName);
            }
        },pageable));
    }

    /**
     * 添加公司信息
     * @param company 公司信息
     * @return 返回结果
     */
    @Override
    public ResponseResult addCompany(Company company) {
        // 判断信息
        if (company == null){
            return ResponseResult.FAILED("公司信息为空");
        }
        // 保存公司信息
        companyDao.save(company);
        return ResponseResult.SUCCESS("添加成功");
    }

    /**
     * 批量添加公司信息
     * @param companies 公司信息列表
     * @return 返回结果
     */
    @Override
    public ResponseResult addCompanies(List<Company> companies) {
        // TODO:批量添加公司信息
        return null;
    }

    /**
     * 修改公司信息
     * @param company 公司信息
     * @return 返回结果
     */
    @Override
    public ResponseResult editCompany(Company company) {
        // 判断公司信息
        if (company == null){
            return ResponseResult.FAILED("公司信息为空");
        }
        // 保存信息
        companyDao.save(company);
        return ResponseResult.SUCCESS("修改成功");
    }

    /**
     * 通过id删除公司信息
     * @param id 公司ID
     * @return 返回结果
     */
    @Override
    public ResponseResult deleteCompanyById(Long id) {
        // 删除公司
        try{
            companyDao.deleteById(id);
        } catch (Exception e){
            return ResponseResult.FAILED("公司不存在");
        }
        return ResponseResult.SUCCESS("删除成功");
    }

    /**
     * 批量删除公司
     * @param ids 公司ID列表
     * @return 返回结果
     */
    @Override
    public ResponseResult deleteCompanies(List<Long> ids) {
        // TODO:批量删除公司
        return null;
    }

    /**
     * 通过关键词搜索公司名字
     * @return 返回结果
     */
    @Override
    public ResponseResult getAllCompanyName() {
        return ResponseResult.SUCCESS("查询所有公司名成功").setData(companyDao.getAllCompanyName());
    }
}
