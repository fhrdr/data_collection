package com.example.data_collection.service.impl;

import com.example.data_collection.dao.CompanyDao;
import com.example.data_collection.dao.StationDao;
import com.example.data_collection.entity.Station;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.ListService;
import com.example.data_collection.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class ListServiceImpl implements ListService {
    // 注入
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private StationDao stationDao;

    /**
     * 根据公司名 模糊查询列表
     * @param companyName 公司名
     * @param page 页码
     * @param size 长度
     * @return 返回结果
     */
    @Override
    public ResponseResult searchCompanies(String companyName, int page, int size) {
        // 设置页码格式
        if (page <= 0){ page = 1; }
        if (size <=0 ){ size = 10; }
        page-=1;
        // 模糊查询
        List<Object[]> companies = companyDao.searchCompanies(companyName, page*size, size);
        // 数据判断
        if (companies.size() == 0) {
            return ResponseResult.FAILED("数据不存在！");
        }
        return ResponseResult.SUCCESS("获取成功！").setData(ListUtils.change(companies, page, size));
    }

    /**
     * 根据岗位名 模糊查询列表
     * @param stationName 岗位名
     * @param page 页码
     * @param size 长度
     * @return 返回结果
     */
    @Override
    public ResponseResult searchStation(String stationName, int page, int size) {
        // 设置页码格式
        if (page <= 0){ page = 1; }
        if (size <=0 ){ size = 10; }
        page-=1;
        // 模糊查询
        List<Object[]> stations = stationDao.searchStations(stationName, page*size, size);
        // 数据判断
        if (stations.size() == 0) {
            return ResponseResult.FAILED("数据不存在！");
        }
        return ResponseResult.SUCCESS("获取成功！").setData(ListUtils.change(stations, page, size));
    }

    /**
     * 列出所有公司信息
     * @param page 页码
     * @param size 长度
     * @return 返回结果
     */
    @Override
    public ResponseResult listAll(int page, int size) {
        // 设置页码格式
        if (page <= 0){ page = 1; }
        if (size <=0 ){ size = 10; }
        page-=1;
        // 查询所有数据
        List<Object[]> all = companyDao.listAll(page*size, size);
        // 判断数据
        if (all.size() == 0) {
            return ResponseResult.FAILED("数据不存在！");
        }
        return ResponseResult.SUCCESS("获取成功！").setData(ListUtils.change(all, page, size));
    }
}
