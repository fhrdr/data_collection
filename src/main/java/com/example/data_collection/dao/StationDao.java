package com.example.data_collection.dao;

import com.example.data_collection.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StationDao extends JpaRepository<Long , Station>,
        JpaSpecificationExecutor<Station> {
}
