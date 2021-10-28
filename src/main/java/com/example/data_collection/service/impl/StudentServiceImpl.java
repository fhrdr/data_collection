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
import com.example.data_collection.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取学生个人基本信息
     * @return 返回信息
     */
    @Override
    public ResponseResult getStudentInfo(HttpServletRequest request) {
        // 获取学生ID
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
                return criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("sId").as(Long.class) , finalSId),
                        criteriaBuilder.equal(root.get("ssStatus").as(Boolean.class) , true)
                );
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

    /**
     * 学生修改密码部分
     * @param request request
     * @return 返回结果
     */
    @Override
    public ResponseResult changePassword(String oldPassword, String newPassword, HttpServletRequest request) {
        // 获取学生ID
        String token = request.getParameter("token");
        String sId = null;
        try {
            Claims claims = JwtUtils.parseJWT(token);
            sId = claims.get("sId").toString();
        } catch (Exception e){
            ResponseResult.FAILED("修改密码失败！");
        }
        // 获取学生密码
        assert sId != null;
        Optional<Student> st = studentDao.findById(Long.valueOf(sId));
        // 验证密码
        String old = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        Student save = st.get();
        if (!old.equals(save.getSPassword())){
            return ResponseResult.FAILED("旧密码错误");
        }
        // 修改密码
        save.setSPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        studentDao.save(save);
        // 返回结果
        return ResponseResult.SUCCESS("修改密码成功！");
    }

    /**
     * 查询所有学生信息
     * @param size 数量
     * @param page 页码
     * @return 返回结果
     */
    @Override
    public ResponseResult findAllStudent(int size, int page) {
        // 判断数值是否超出
        if (size<=0){
            size = 10;
        }
        if (page<=0){
            page = 1;
        }
        page -= 1;
        // 定义分页
        Pageable pageable = PageRequest.of(page, size);
        // 返回结果
        return ResponseResult.SUCCESS("查找成功").setData(studentDao.findAll(pageable));
    }

    /**
     * 查询所有班级
     * @return 返回所有班级
     */
    @Override
    public ResponseResult findAllClass() {
        return ResponseResult.SUCCESS("获取班级成功").setData(studentDao.findAllClass());
    }

    /**
     * 通过班级查询所有学生
     * @param size 数量
     * @param page 页码
     * @return 返回结果
     */
    @Override
    public ResponseResult findAllStudentsByClass(String className,int size, int page) {
        // 判断数值是否超出
        if (size<=0){
            size = 10;
        }
        if (page<=0){
            page = 1;
        }
        page -= 1;
        // 查询所有学生
        List<Object[]> students = studentDao.findAllStudentsByClass(className, size * page, size);
        List<Map> results = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        String[] name = {"s_id","s_number","s_college","s_department","s_name","s_class","s_choice"};
        for (int i = 0; i < students.size(); i++) {
            for (int j = 0; j < students.get(i).length; j++) {
                result.put(name[j],students.get(i)[j]);
            }
            results.add(result);
            result = new HashMap<>();
        }
        // 返回结果
        return ResponseResult.SUCCESS("获取成功").setData(results);
    }

    /**
     * 查询所有学生基本信息
     * @return 返回结果
     */
    @Override
    public ResponseResult findAllStudents() {
        // 查询所有学生
        List<Object[]> students = studentDao.findAllStudents();
        List<Map> results = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        String[] name = {"s_id","s_number","s_college","s_department","s_name","s_class","s_choice"};
        for (int i = 0; i < students.size(); i++) {
            for (int j = 0; j < students.get(i).length; j++) {
                result.put(name[j],students.get(i)[j]);
            }
            results.add(result);
            result = new HashMap<>();
        }
        return ResponseResult.SUCCESS("获取成功").setData(results);
    }

    /**
     * 通过部门查询学生信息
     * @param departmentName 部门名
     * @return 返回结果
     */
    @Override
    public ResponseResult findStudentByDepartment(String departmentName) {
        return ResponseResult.SUCCESS("查找成功").setData(studentDao.findAll(new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("sDepartment") , "%"+departmentName+"%");
            }
        }));
    }

    /**
     * 添加学生信息
     * @param student 学生信息
     * @return 返回结果
     */
    @Override
    public ResponseResult addStudent(Student student) {
        // 判断
        if (student == null){
            return ResponseResult.FAILED("学生信息为空");
        }
        // 保存信息
        studentDao.save(student);
        return ResponseResult.SUCCESS("添加成功");
    }

    /**
     * 批量添加学生信息
     * @param students 学生信息列表
     * @return 返回结果
     */
    @Override
    public ResponseResult addStudents(List<Student> students) {
        // TODO:批量添加学生信息
        return null;
    }

    /**
     * 修改学生信息
     * @param student 学生信息
     * @return 返回结果
     */
    @Override
    public ResponseResult editStudent(Student student) {
        // 判断
        if (student == null){
            return ResponseResult.FAILED("学生信息为空");
        }
        // 保存数据
        studentDao.save(student);
        return ResponseResult.SUCCESS("修改成功");
    }

    /**
     * 通过id删除学生信息
     * @param sId 学生ID
     * @return 返回结果
     */
    @Override
    public ResponseResult deleteStudent(Long sId) {
        try{
            studentDao.deleteById(sId);
        }catch (Exception e){
            return ResponseResult.FAILED("学生不存在");
        }
        return ResponseResult.SUCCESS("删除成功");
    }

    /**
     * 批量删除学生信息
     * @param ids 学生ID列表
     * @return 返回结果
     */
    @Override
    public ResponseResult deleteStudents(List<Long> ids) {
        // TODO:批量删除学生信息
        return null;
    }
}
