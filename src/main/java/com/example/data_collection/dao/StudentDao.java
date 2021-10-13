package com.example.data_collection.dao;

import com.example.data_collection.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentDao extends JpaRepository<Student , Long>,
        JpaSpecificationExecutor<Student> {

//通过部门名查找学生信息
    @Query(nativeQuery = true ,
            value ="select " +
                    "* from t_student " +
                    "where s_department like %?1%")
    List<Student> findBysDepartment(String sDepartment);

    //通过id删除学生
    @Query(value = "delete from t_student where s_id = ?1",nativeQuery = true)
    int deleteByid(Long sId);
}
