package com.example.data_collection.service.impl;

import com.example.data_collection.dao.AdminDao;
import com.example.data_collection.entity.Admin;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.print.Pageable;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    //注入
    @Autowired
    private AdminDao adminDao;

    /**
     * 查询所有管理员
     * @return 返回所有管理员信息
     */
    @Override
    public ResponseResult findAllAdmin() {
        return ResponseResult.SUCCESS("获取管理员成功").setData(adminDao.findAll());
    }

    /**
     * 通过ID查询管理员
     * @param id 管理员ID
     * @return 返回管理员信息
     */
    @Override
    public ResponseResult findAdminById(Long id) {
        return ResponseResult.SUCCESS("查找管理员成功").setData(adminDao.findById(id).orElse(null));
    }

    /**
     * 添加管理员信息
     * @param admin 管理员信息
     * @return 返回结果
     */
    @Override
    public ResponseResult addAdmin(Admin admin) {
        // 判断是否存在
        List<Admin> admins = adminDao.findAll(new Specification<Admin>() {
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name") , admin.getName());
            }
        });
        if (admins.size() > 0){
            return ResponseResult.FAILED("管理员 "+admin.getName()+" 已经存在");
        }
        // 设置信息
        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        // 保存信息
        adminDao.save(admin);
        return ResponseResult.SUCCESS("添加管理员成功");
    }

    /**
     * 更新管理员信息
     * @param admin 管理员信息
     * @return 返回结果
     */
    @Override
    public ResponseResult editAdmin(Admin admin) {
        // 判断信息是否正确
        if (admin.getName() == null || admin.getPassword() == null || admin.getId() == null){
            return ResponseResult.FAILED("请输入正确的信息");
        }
        // 设置信息
        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        // 保存信息
        adminDao.save(admin);
        return ResponseResult.SUCCESS("修改管理员成功");
    }

    @Override
    public ResponseResult deleteAdminById(Long id) {
        // 删除管理员
        try {
            adminDao.deleteByid(id);
        } catch (Exception e){
            return ResponseResult.FAILED("管理员不存在");
        }
        return ResponseResult.SUCCESS("删除管理员成功");
    }
}
