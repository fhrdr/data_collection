package com.example.data_collection.dao;

import com.example.data_collection.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminDao extends JpaRepository<Long , Admin>,
        JpaSpecificationExecutor<Admin> {
}
