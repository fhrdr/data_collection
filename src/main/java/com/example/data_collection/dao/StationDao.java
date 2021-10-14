package com.example.data_collection.dao;

import com.example.data_collection.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationDao extends JpaRepository<Station , Long>,
        JpaSpecificationExecutor<Station> {

    // 通过岗位名 模糊查询
    @Query(nativeQuery = true , value = "SELECT\n" +
            "\tb.st_id,b.st_name,a.c_name,b.st_pay,b.st_need,b.st_duration\n" +
            "FROM\n" +
            "\tt_company AS a\n" +
            "\tINNER JOIN\n" +
            "\tt_station AS b\n" +
            "\tON \n" +
            "\t\ta.c_id = b.c_id" +
            "\tWHERE" +
            "\tb.st_name like %?1%"+
            "\tLIMIT ?2,?3")
    List<Object[]> searchStations(String stationName, int start, int end);

    // 通过岗位ID 查询公司信息，岗位信息和学生报名数
    @Query(nativeQuery = true , value ="SELECT\n" +
            "\ta.c_id, c_name, c_people_num, c_nature, c_introduction, c_address, st_id, st_name, st_need, " +
            "st_describe, st_pay, st_duration, st_start, st_end, station_num, limit_num, is_choose\n" +
            "FROM\n" +
            "\tt_company AS a\n" +
            "\tLEFT JOIN\n" +
            "\tt_station AS b \n" +
            "\tON\n" +
            "\ta.c_id = b.c_id,\n" +
            "\t(SELECT COUNT(*) station_num FROM t_student_station WHERE st_id=?1 and ss_status=1) AS c,\n" +
            "\t(SELECT COUNT(*)<(\n" +
            "\tSELECT s_choice FROM t_student WHERE s_id=?2 and ss_status=1\n" +
            "\t) limit_num FROM t_student_station WHERE s_id=?2 and ss_status=1) AS d," +
            "\t(SELECT count(*) is_choose FROM t_student_station WHERE st_id=?1 and ss_status=1 and s_id=?2) AS e\n" +
            "WHERE\n" +
            "\tb.st_id =?1" )
    List<Object[]> selectInfo(Long stId , Long sId);

    // 杨修伟部分
    //查询岗位信息以及对应公司名
    @Query(value = "select " +
            "st_name, " +
            "st_describe," +
            " st_duration," +
            " st_pay," +
            " st_start," +
            " st_end," +
            " c_name" +
            " from t_station,t_company " +
            "where t_station.c_id = t_company.c_id",nativeQuery = true)
    List<Object[]> findAllStationAndCompany();

    //通过公司名查询岗位信息
    @Query(value = "select " +
            "t_station.* " +
            "from t_station " +
            "where (SELECT c_name from t_company where t_company.c_id = t_station.c_id) " +
            " = #{c_name}",nativeQuery = true)
    List<Station> findStationByCname(String cName);

    //通过id删除岗位
    @Query(value = "delete from t_station where st_id = ?1",nativeQuery = true)
    int deleteByid(Long stId);
}
