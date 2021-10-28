package com.example.data_collection.service.impl;

import com.example.data_collection.dao.AdminDao;
import com.example.data_collection.dao.StudentDao;
import com.example.data_collection.utils.JwtUtils;
import com.example.data_collection.utils.PrintIpAddress;
import com.example.data_collection.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Service("permission")
public class PermissionService {
    // 注入
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 判断登录与否
     * @return 返回判断
     */
    public boolean student() {
        // 拿到request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getParameter("token");
        // 没有key，则返回错误
        if (token == null) {
            return false;
        }
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e){
            return false;
        }
        // 日志打印 IP 地址
        String ip = PrintIpAddress.getIpAddress(request);
        // 从 redis 获取 token
        String redisToken = null;
        if (redisUtil.hasKey("token"+ip)){
            redisToken = (String) redisUtil.get("token"+ip);
        }
        if (redisToken==null){
            return false;
        }
        return redisToken.equals(token);
    }

    public boolean admin() {
        // 拿到request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getParameter("token");
        // 没有key，则返回错误
        if (token == null) {
            return false;
        }
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e){
            return false;
        }
        // 日志打印 IP 地址
        String ip = PrintIpAddress.getIpAddress(request);
        // 从 redis 获取 token
        String redisToken = null;
        if (redisUtil.hasKey("admin-token"+ip)){
            redisToken = (String) redisUtil.get("admin-token"+ip);
        }
        if (redisToken==null){
            return false;
        }
        return redisToken.equals(token);
    }
}
