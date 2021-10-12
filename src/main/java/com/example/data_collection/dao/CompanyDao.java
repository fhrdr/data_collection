package com.example.data_collection.dao;

import com.example.data_collection.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyDao extends JpaRepository<Company , Long>,
        JpaSpecificationExecutor<Company> {
}
