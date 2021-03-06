package com.example.data_collection.service.impl;

import com.example.data_collection.dao.StationDao;
import com.example.data_collection.dao.StudentStationDao;
import com.example.data_collection.entity.Station;
import com.example.data_collection.entity.StudentStation;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StudentStationService;
import com.example.data_collection.utils.JwtUtils;
import com.example.data_collection.utils.RedisUtil;
import com.example.data_collection.utils.TimeUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class StudentStationServiceImpl implements StudentStationService {
    // 注入
    @Autowired
    private StudentStationDao studentStationDao;
    @Autowired
    private StationDao stationDao;

    /**
     * 学生选择岗位
     * @param stId 岗位ID
     * @return 返回选择结果
     */
    @Override
    public ResponseResult studentChooseStation(Long stId, HttpServletRequest request) {
        // 判断是否在规定时间范围内
        Station station = stationDao.getById(stId);
        if (!TimeUtils.compareTime(station.getStStart(),station.getStEnd())){
            return ResponseResult.FAILED("操作不在规定时间范围内");
        };
        // 获取token
        String token = request.getParameter("token");
        String sId = null;
        try {
            Claims claims = JwtUtils.parseJWT(token);
            sId = claims.get("sId").toString();
        } catch (Exception e){
            return ResponseResult.FAILED("请重新登录！");
        }
        if (sId == null){
            return ResponseResult.FAILED("请重新登录！");
        }
        Long finalSId = Long.valueOf(sId);
        // 判断是否选择满了
        if (Integer.parseInt(studentStationDao.findChooseCount(finalSId).toString()) >= 3){
            return ResponseResult.FAILED("已经超过最大选择限制！");
        }
        // 判断是否存在
        List<StudentStation> studentStations = studentStationDao.findAll(new Specification<StudentStation>() {
            @Override
            public Predicate toPredicate(Root<StudentStation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("stId").as(Long.class) , stId),
                        criteriaBuilder.equal(root.get("sId").as(Long.class) , finalSId)
                );
            }
        });
        if (studentStations.size() > 0){
            // 如果状态码为false，则认为是再次选择，否则则是重复选择
            if (!studentStations.get(0).getSsStatus()){
                StudentStation studentStation = studentStations.get(0);
                studentStation.setSsStatus(true);
                studentStationDao.save(studentStation);
                return ResponseResult.SUCCESS("选择岗位成功！");
            }else {
                return ResponseResult.FAILED("已经选择该岗位了！");
            }
        }
        // 添加信息
        StudentStation studentStation = new StudentStation();
        studentStation.setSId(finalSId);
        studentStation.setStId(stId);
        studentStation.setSsStatus(true);
        studentStation.setSsTime(new Date());
        studentStationDao.save(studentStation);
        // 返回结果
        return ResponseResult.SUCCESS("选择岗位成功！");
    }

    /**
     * 学生取消岗位
     * @param stId 岗位ID
     * @return 返回选择结果
     */
    @Override
    public ResponseResult studentCancelStation(Long stId, HttpServletRequest request) {
        // 判断是否在规定时间范围内
        Station station = stationDao.getById(stId);
        if (!TimeUtils.compareTime(station.getStStart(),station.getStEnd())){
            return ResponseResult.FAILED("操作不在规定时间范围内");
        };
        // 获取token
        String token = request.getParameter("token");
        String sId = null;
        try {
            Claims claims = JwtUtils.parseJWT(token);
            sId = claims.get("sId").toString();
        } catch (Exception e){
            return ResponseResult.FAILED("请重新登录！");
        }
        if (sId == null){
            return ResponseResult.FAILED("请重新登录！");
        }
        // 判断是否存在
        Long finalSId = Long.valueOf(sId);
        List<StudentStation> studentStations = studentStationDao.findAll(new Specification<StudentStation>() {
            @Override
            public Predicate toPredicate(Root<StudentStation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("stId").as(Long.class) , stId),
                        criteriaBuilder.equal(root.get("sId").as(Long.class) , finalSId)
                );
            }
        });
        if (studentStations.size() == 0 ){
            return ResponseResult.FAILED("还没有选择岗位！");
        }
        // 修改状态码
        StudentStation studentStation = studentStations.get(0);
        studentStation.setSsStatus(false);
        // 更新时间
        studentStationDao.save(studentStation);
        // 返回结果
        return ResponseResult.SUCCESS("取消选择成功！");
    }

    /**
     * 添加学生-岗位信息
     * @param studentStation 学生-岗位信息
     * @return 返回结果
     */
    @Override
    public ResponseResult addStudentStation(StudentStation studentStation) {
        // 判断
        if (studentStation == null){
            return ResponseResult.FAILED("学生岗位信息为空");
        }
        // 保存信息
        studentStationDao.save(studentStation);
        return ResponseResult.SUCCESS("添加学生-岗位成功");
    }

    /**
     * 关联查询学生-岗位-公司信息
     * @return 返回结果
     */
    @Override
    public ResponseResult findStudentInfo() {
        return ResponseResult.SUCCESS("查询成功").setData(studentStationDao.findAllSs());
    }

    /**
     * 通过班级进行关联查询学生-岗位信息
     * @param sClass 班级名称
     * @return 返回结果
     */
    @Override
    public ResponseResult findBySClass(String sClass) {
        return ResponseResult.SUCCESS("查询成功").setData(studentStationDao.findBySClass(sClass));
    }
}
