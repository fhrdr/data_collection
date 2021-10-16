package com.example.data_collection.service.impl;

import com.example.data_collection.dao.CompanyDao;
import com.example.data_collection.dao.StationDao;
import com.example.data_collection.entity.Station;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.ListService;
import com.example.data_collection.utils.JwtUtils;
import com.example.data_collection.utils.ListUtils;
import com.example.data_collection.utils.PrintIpAddress;
import com.example.data_collection.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ListServiceImpl implements ListService {
    // 注入
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private StationDao stationDao;
    @Autowired
    private RedisUtil redisUtil;

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
        return ResponseResult.SUCCESS("获取成功！").setData(ListUtils.change(all, page, size));
    }

    /**
     * 根据岗位ID 查询详细信息
     * @param stId 岗位ID
     * @return 返回信息
     */
    @Override
    public ResponseResult selectInfo(Long stId, HttpServletRequest request) {
        // 获取token
        String token = request.getParameter("token");
        String sId = null;
        try {
            Claims claims = JwtUtils.parseJWT(token);
            sId = claims.get("sId").toString();
        } catch (Exception e){
            ResponseResult.FAILED("请重新登录！");
        }
        // 判断sId
        if (sId == null){
            return ResponseResult.FAILED("请重新登录！");
        }
        // 查询信息
        List<Object[]> objects = stationDao.selectInfo(stId, Long.valueOf(sId));
        // 判断
        if (objects.size() == 0) {
            return ResponseResult.FAILED("数据为空！");
        }
        // 提取值
        Map<String, Object> result = ListUtils.getInfo(objects);
        // 返回结果
        return ResponseResult.SUCCESS("查询成功！").setData(result);
    }
}
