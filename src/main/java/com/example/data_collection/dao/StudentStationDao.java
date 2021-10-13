package com.example.data_collection.dao;

import com.example.data_collection.entity.StudentStation;
import com.example.data_collection.entity.StudentStationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentStationDao extends JpaRepository<StudentStation , Long>,
        JpaSpecificationExecutor<StudentStation> {

    @Query(value = "select new com.example.data_collection.entity.StudentStationInfo(s_department, s_class, s_name, c_name, st_name, ss_time) from t_student_station left join t_student on t_student_station.s_id = t_student.s_id left join t_station on t_student_station.st_id=t_station.st_id LEFT  JOIN t_company on t_company.c_id=t_station.c_id",nativeQuery = true)
    List<StudentStationInfo> findAllSs();
}
