package com.example.data_collection.service.impl;

import com.example.data_collection.dao.AdminDao;
import com.example.data_collection.dao.StudentDao;
import com.example.data_collection.entity.Admin;
import com.example.data_collection.entity.Student;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.LoginService;
import com.example.data_collection.utils.JwtUtils;
import com.example.data_collection.utils.PrintIpAddress;
import com.example.data_collection.utils.RedisUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    // 注入
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 学生登录接口
     * @param number 学生账号
     * @param password 学生密码
     * @return 返回结果 和 token
     */
    @Override
    public ResponseResult studentLogin(String number, String password , HttpServletRequest request) {
        // 判断是否为空
        if (number == null){
            return ResponseResult.FAILED("账号为空");
        }
        if (password == null){
            return ResponseResult.FAILED("密码为空");
        }
        // 查询账号
        List<Student> students = studentDao.findAll(new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("sNumber") , number);
            }
        });
        // 密码加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 登录判断
        if (students.size() == 0){
            return ResponseResult.FAILED("账号错误");
        } else if(!password.equals(students.get(0).getSPassword())){
            return ResponseResult.FAILED("密码错误");
        }
        // 生成 token
        Map<String, Object> claims = new HashMap<>();
        claims.put("sId", students.get(0).getSId());
        claims.put("number", number);
        claims.put("name", students.get(0).getSName());
        claims.put("class", students.get(0).getClass());
        claims.put("phone", students.get(0).getSPhone());
        String token = JwtUtils.createToken(claims);
        // 日志打印 IP 地址
        String ip = PrintIpAddress.getIpAddress(request, "登录学生选择系统...");
        // 存放 token
        redisUtil.set("token"+ip, token);
        // 返回成功
        return ResponseResult.SUCCESS("登录成功").setData(token);
    }

    /**
     * 管理员登录接口
     * @param number 管理员账号
     * @param password 学管理员密码
     * @return 返回结果 和 token
     */
    @Override
    public ResponseResult adminLogin(String number, String password, HttpServletRequest request) {
        // 判断是否为空
        if (number == null && password == null){
            return ResponseResult.FAILED("账号为空！");
        }
        // 查询账号
        List<Admin> admins = adminDao.findAll(new Specification<Admin>() {
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), number);
            }
        });
        // 密码加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 登录判断
        if (admins.size() == 0){
            return ResponseResult.FAILED("账号错误！");
        } else if(!password.equals(admins.get(0).getPassword())){
            return ResponseResult.FAILED("密码错误！");
        }
        // 生成 token
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("name", number);
        String token = JwtUtils.createToken(claims);
        // 日志打印 IP 地址
        String ip = PrintIpAddress.getIpAddress(request, "登录后台系统...");
        // 存放token
        redisUtil.set("admin-token"+ip, token);
        // 返回成功
        return ResponseResult.SUCCESS("登录成功！").setData(token);
    }

    /**
     * 注销登录
     * @return 返回结果
     */
    @Override
    public ResponseResult LoginOut(HttpServletRequest request) {
        // 获取token
        String token = request.getParameter("token");
        // 日志打印 IP 地址
        String ip = PrintIpAddress.getIpAddress(request, "退出登录...");
        // 删除session中的token
        if (redisUtil.hasKey("token"+ip)){
            redisUtil.del("token"+ip);
        }
        return ResponseResult.SUCCESS("注销登录成功！");
    }
}
