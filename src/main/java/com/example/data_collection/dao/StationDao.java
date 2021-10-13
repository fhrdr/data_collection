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
}
