package com.example.data_collection.dao;

import com.example.data_collection.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentDao extends JpaRepository<Student , Long>,
        JpaSpecificationExecutor<Student> {


}
