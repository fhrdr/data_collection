package com.example.data_collection.service.impl;

import com.example.data_collection.dao.StationDao;
import com.example.data_collection.dao.StudentDao;
import com.example.data_collection.dao.StudentStationDao;
import com.example.data_collection.entity.Station;
import com.example.data_collection.entity.Student;
import com.example.data_collection.entity.StudentStation;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StudentService;
import com.example.data_collection.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    // 注入
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentStationDao studentStationDao;
    @Autowired
    private StationDao stationDao;

    /**
     * 获取学生个人基本信息
     * @return 返回信息
     */
    @Override
    public ResponseResult getStudentInfo(HttpSession session) {
        // 获取学生ID
        String token = (String) session.getAttribute("token");
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
        // 查询学生个人信息
        List<Student> students = studentDao.findAll(new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("sId").as(Long.class) , finalSId);
            }
        });
        if (students.size() == 0){
            System.out.println(students);
            return ResponseResult.FAILED("请重新登录！");
        }
        Student student = students.get(0);
        // 查询学生选择的岗位
        List<StudentStation> studentStations = studentStationDao.findAll(new Specification<StudentStation>() {
            @Override
            public Predicate toPredicate(Root<StudentStation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("sId").as(Long.class) , finalSId);
            }
        });
        // 数据处理
        HashMap<String, Object> result = new HashMap<>();
        result.put("stations", "");
        result.putAll(student.toHashMap());
        List<Station> stations = new ArrayList<>();
        for (StudentStation studentStation : studentStations){
            stations.add(stationDao.findAll(new Specification<Station>() {
                @Override
                public Predicate toPredicate(Root<Station> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.equal(root.get("stId").as(Long.class) , studentStation.getStId());
                }
            }).get(0));
        }
        result.put("stations", stations);
        return ResponseResult.SUCCESS("获取成功！").setData(result);
    }
}
