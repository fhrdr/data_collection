package com.example.data_collection.dao;

import com.example.data_collection.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentDao extends JpaRepository<Student , Long>,
        JpaSpecificationExecutor<Student> {

    // 获取所有班级
    @Query(value = "SELECT DISTINCT s_class FROM t_student", nativeQuery = true)
    List<Object> findAllClass();

    // 通过班级模糊查询学生基本信息
    @Query(value = "SELECT s_id, s_number, s_college, s_department, s_name, s_class,\n" +
            "( SELECT COUNT(*) FROM t_student_station WHERE t_student_station.s_id = t_student.s_id and ss_status=1 ) s_choice \n" +
            "FROM\n" +
            "\tt_student\n" +
            "WHERE\n" +
            "\ts_class like %?1%" +
            "\tLIMIT ?2,?3", nativeQuery = true)
    List<Object[]> findAllStudentsByClass(String className , int page, int size);

    // 查询学生所有学生基本信息
    @Query(value = "SELECT s_id, s_number, s_college, s_department, s_name, s_class,\n" +
            "( SELECT COUNT(*) FROM t_student_station WHERE t_student_station.s_id = t_student.s_id and ss_status=1 ) s_choice \n" +
            "FROM\n" +
            "\tt_student\n", nativeQuery = true)
    List<Object[]> findAllStudents();
}

