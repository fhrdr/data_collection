package com.example.data_collection.dao;

import com.example.data_collection.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentDao extends JpaRepository<Student , Long>,
        JpaSpecificationExecutor<Student> {

    @Query(nativeQuery = true , value ="select * from t_student where s_department like %?1%")
    List<Student> findBysDepartment(String sDepartment);
}
