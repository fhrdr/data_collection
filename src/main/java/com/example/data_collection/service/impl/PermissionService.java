package com.example.data_collection.service.impl;

import com.example.data_collection.dao.AdminDao;
import com.example.data_collection.dao.StudentDao;
import com.example.data_collection.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service("permission")
public class PermissionService {
    // 注入
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private StudentDao studentDao;

    /**
     * 判断登录与否
     * @return 返回判断
     */
    public boolean use() {
        //拿到request和response
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession();
        String token = request.getHeader("token");
        System.out.println(token);
        // 没有key，则返回错误
        if (token == null) {
            return false;
        }
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e){
            return false;
        }
        if (session.getAttribute("token")==null){
            return false;
        }
        return session.getAttribute("token").equals(token);
    }

}
