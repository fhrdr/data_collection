package com.example.data_collection.controller.back;

import com.example.data_collection.entity.Company;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.CompanyService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/back/company")
public class CompanyController {
    //注入
    @Resource
    private CompanyService companyService;

    /**
     * 通过公司名查询公司信息
     * @param cName
     * @return
     */
    @RequestMapping("/findByCname")
    public ResponseResult getCompanyByCname(String cName){
        return companyService.findByCname(cName);
    }

    /**
     * 添加公司信息
     * @param company
     * @return
     */
    @RequestMapping("/add")
    public ResponseResult addCompany(Company company){
        return companyService.addCompany(company);
    }

    /**
     * 修改公司信息
     * @param company
     * @return
     */
    @RequestMapping("/update")
    public ResponseResult updateCompany(Company company){
        return companyService.updateCompany(company);
    }

    /**
     * 通过id删除公司信息
     * @param cId
     * @return
     */
    @RequestMapping("/delete")
    public ResponseResult deleteStudent(@PathParam("cId") Long cId){
        return companyService.deleteStudent(cId);
    }
}
