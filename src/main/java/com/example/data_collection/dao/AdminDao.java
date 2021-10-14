package com.example.data_collection.dao;

import com.example.data_collection.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AdminDao extends JpaRepository<Admin , Long>,
        JpaSpecificationExecutor<Admin> {

    //通过id删除管理员
    @Query(value = "delete from t_admin where id = ?1",nativeQuery = true)
    int deleteByid(Long Id);
}
