package com.example.data_collection.controller.back;

import com.example.data_collection.entity.Company;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@PreAuthorize("@permission.admin()")
@RequestMapping("/back")
public class CompanyController {
    //注入
    @Autowired
    private CompanyService companyService;

    /**
     * 查询所有的公司
     * @param size 数量
     * @param page 页码
     * @return 返回结果
     */
    @GetMapping("/findAllCompany/{size}/{page}")
    public ResponseResult findAllCompany(@PathVariable("size") int size,
                                         @PathVariable("page") int page){
        return companyService.findAllCompany(page , size);
    }

    /**
     * 通过公司名查询公司信息
     * @param size 数量
     * @param page 页码
     * @param companyName 公司名
     * @return 返回结果
     */
    @RequestMapping("/findCompanyByName/{size}/{page}")
    public ResponseResult findCompanyByName(@PathVariable("size") int size ,
                                            @PathVariable("page") int page,
                                            @PathParam("name") String companyName){
        return companyService.findCompanyByName(page , size , companyName);
    }

    /**
     * 添加公司信息
     * @param company 公司信息
     * @return 返回结果
     */
    @RequestMapping("/addCompany")
    public ResponseResult addCompany(Company company){
        return companyService.addCompany(company);
    }

    /**
     * 修改公司信息
     * @param company 公司信息
     * @return 返回结果
     */
    @RequestMapping("/editCompany")
    public ResponseResult editCompany(Company company){
        return companyService.editCompany(company);
    }

    /**
     * 通过id删除公司信息
     * @param cId 公司ID
     * @return 返回结果
     */
    @DeleteMapping("/deleteCompany/{cId}")
    public ResponseResult deleteCompany(@PathVariable("cId") Long cId){
        return companyService.deleteCompanyById(cId);
    }

    /**
     * 查询所有的公司名
     * @return 返回结果
     */
    @GetMapping("/getAllCompanyName")
    public ResponseResult getAllCompanyName(){
        return companyService.getAllCompanyName();
    }

    /**
     * 批量添加公司
     * @param companies 公司列表
     * @return 返回结果
     */
    @PostMapping("/addCompanies")
    public ResponseResult addCompanies(List<Company> companies){
        return companyService.addCompanies(companies);
    }

    /**
     * 批量删除公司
     * @param ids 公司id列表
     * @return 返回结果
     */
    @DeleteMapping("/deleteCompanies")
    public ResponseResult deleteCompanies(List<Long> ids){
        return companyService.deleteCompanies(ids);
    }
}
