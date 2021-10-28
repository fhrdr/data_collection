package com.example.data_collection.service.impl;

import com.example.data_collection.dao.CompanyDao;
import com.example.data_collection.dao.StationDao;
import com.example.data_collection.entity.Station;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StationService;
import com.example.data_collection.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StationServiceImpl implements StationService {
    //注入接口
    @Autowired
    private StationDao stationDao;
    @Autowired
    private CompanyDao companyDao;

    /**
     * 查询所有岗位信息
     * @param page 页码
     * @param size 数量
     * @return 返回结果
     */
    @Override
    public ResponseResult findAllStation(int page , int size) {
        // 归正page和size
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0){
            size = 20;
        }
        page -= 1;
        // 设置分页
        Pageable pageable = PageRequest.of(page , size);
        // 查询所有的岗位信息
        return ResponseResult.SUCCESS("查找成功").setData(stationDao.findAll(pageable));
    }

    /**
     * 通过岗位名查询岗位
     * @param page 页码
     * @param size 数量
     * @param stationName 岗位名字
     * @return 返回结果
     */
    @Override
    public ResponseResult findStationByName(int page, int size, String stationName) {
        // 归正page和size
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0){
            size = 20;
        }
        page -= 1;
        // 查询所有数据
        List<Object[]> stations = stationDao.getStationsByName(stationName, page * size, size);
        // 数据处理
        List<Map<String , Object>> result = ListUtils.getStations(stations);
        // 返回结果
        return ResponseResult.SUCCESS("查询岗位成功").setData(result);
    }

    /**
     * 通过公司名查询岗位
     * @param page 页码
     * @param size 数量
     * @param companyName 公司名字
     * @return 返回结果
     */
    @Override
    public ResponseResult findStationByCompany(int page, int size, String companyName) {
        // 归正page和size
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0){
            size = 20;
        }
        page -= 1;
        // 查询所有数据
        List<Object[]> stations = stationDao.getStationsByCompanyName(companyName, page * size, size);
        // 数据处理
        List<Map<String , Object>> result = ListUtils.getStations(stations);
        return ResponseResult.SUCCESS("查询岗位成功").setData(result);
    }

    /**
     * 添加岗位
     * @param station 岗位信息
     * @return 返回结果
     */
    @Override
    public ResponseResult addStation(Station station) {
        // 判断
        if (station == null){
            return ResponseResult.FAILED("岗位信息为空");
        }
        if (station.getCId() == null){
            return ResponseResult.FAILED("公司不能为空");
        }
        // 保存数据
        stationDao.save(station);
        return ResponseResult.SUCCESS("添加岗位成功");
    }

    /**
     * 批量添加岗位
     * @param stations 岗位信息列表
     * @return 返回结果
     */
    @Override
    public ResponseResult addStations(List<Station> stations) {
        // TODO:批量添加岗位
        return null;
    }

    /**
     * 修改岗位信息
     * @param station 岗位信息
     * @return 返回结果
     */
    @Override
    public ResponseResult editStation(Station station) {
        if (station == null) {
            return ResponseResult.FAILED("岗位信息为空");
        }
        return ResponseResult.SUCCESS("修改成功").setData(stationDao.save(station));
    }

    /**
     * 通过id删除岗位信息
     * @param stId 岗位id
     * @return 返回结果
     */
    @Override
    public ResponseResult deleteStation(Long stId) {
        // 删除岗位
        try {
            stationDao.deleteById(stId);
        }catch (Exception e){
            return ResponseResult.FAILED("岗位不存在");
        }
        return ResponseResult.SUCCESS("删除成功");
    }

    /**
     * 批量删除岗位
     * @param ids 岗位ID列表
     * @return 返回结果
     */
    @Override
    public ResponseResult deleteStations(List<Long> ids) {
        // TODO:批量删除岗位
        return null;
    }
}
