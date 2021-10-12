package com.example.data_collection.dao;

import com.example.data_collection.entity.StudentStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentStationDao extends JpaRepository<StudentStation , Long>,
        JpaSpecificationExecutor<StudentStation> {
}
