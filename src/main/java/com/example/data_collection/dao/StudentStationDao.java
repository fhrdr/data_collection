package com.example.data_collection.dao;

import com.example.data_collection.entity.StudentStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentStationDao extends JpaRepository<StudentStation , Long>,
        JpaSpecificationExecutor<StudentStation> {
    //关联查询学生信息，岗位信息，部门信息，公司、选择时间信息
    @Query(value = "SELECT\n" +
            "\ts_number,\n" +
            "\ts_college,\n" +
            "\ts_department,\n" +
            "\ts_class,\n" +
            "\ts_name,\n" +
            "\ts_phone,\n" +
            "\tc_name,\n" +
            "\tst_name,\n" +
            "\tss_time \n" +
            "FROM\n" +
            "\t(t_student_station\n" +
            "\tLEFT JOIN t_student ON t_student_station.s_id = t_student.s_id\n" +
            "\tLEFT JOIN t_station ON t_student_station.st_id = t_station.st_id\n" +
            "\tLEFT JOIN t_company ON t_company.c_id = t_station.c_id)\n" +
            "WHERE\n" +
            "\tt_student_station.ss_status=1",nativeQuery = true)
    List<Object[]> findAllSs();

    //通过班级进行关联查询
    @Query(value = "SELECT\n" +
            "\ts_number,\n" +
            "\ts_college,\n" +
            "\ts_department,\n" +
            "\ts_class,\n" +
            "\ts_name,\n" +
            "\ts_phone,\n" +
            "\tc_name,\n" +
            "\tst_name,\n" +
            "\tss_time \n" +
            "FROM\n" +
            "\tt_student_station\n" +
            "\tLEFT JOIN t_student ON t_student_station.s_id = t_student.s_id\n" +
            "\tLEFT JOIN t_station ON t_student_station.st_id = t_station.st_id\n" +
            "\tLEFT JOIN t_company ON t_company.c_id = t_station.c_id \n" +
            "WHERE\n" +
            "\ts_class LIKE %?1% AND t_student_station.ss_status=1",nativeQuery = true)
    List<Object[]> findBySClass(String sClass);
}
