package com.example.data_collection.controller.portal;

import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/portal")
public class ListController {
    // 注入
    @Autowired
    private ListService listService;

    /**
     * 通过公司名 模糊查询列表
     * @param size 长度
     * @param page 页码
     * @param companyName 公司名
     * @return 返回结果
     */
    @PreAuthorize("@permission.use()")
    @GetMapping("/searchCompany/{size}/{page}/{companyName}")
    ResponseResult searchCompany(@PathVariable("size") int size,
                                 @PathVariable("page") int page,
                                 @PathVariable("companyName") String companyName){
        return listService.searchCompanies(companyName, page, size);
    }

    /**
     * 通过岗位名 模糊查询列表
     * @param size 长度
     * @param page 页码
     * @param stationName 岗位名
     * @return 返回结果
     */
    @PreAuthorize("@permission.use()")
    @GetMapping("/searchStation/{size}/{page}/{stationName}")
    ResponseResult searchStation(@PathVariable("size") int size,
                                 @PathVariable("page") int page,
                                 @PathVariable("stationName") String stationName){
        return listService.searchStation(stationName, page, size);
    }

    /**
     * 获取所有列表
     * @param size 长度
     * @param page 页码
     * @return 返回结果
     */
    @PreAuthorize("@permission.use()")
    @GetMapping("/listAll/{size}/{page}")
    ResponseResult listAll(@PathVariable("size") int size,
                             @PathVariable("page") int page){
        return listService.listAll(page, size);
    }

    /**
     * 通过岗位ID 查询详细信息
     * @param stId 岗位ID
     * @return 返回查询结果
     */
    @PreAuthorize("@permission.use()")
    @GetMapping("/selectInfo/{stId}")
    ResponseResult selectInfo(@PathVariable("stId") Long stId , HttpServletRequest request){
        return listService.selectInfo(stId, request);
    }
}
