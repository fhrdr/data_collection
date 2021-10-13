package com.example.data_collection.dao;

import com.example.data_collection.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyDao extends JpaRepository<Company , Long>,
        JpaSpecificationExecutor<Company> {

    // 获取所有列表信息
    @Query(nativeQuery = true , value = "SELECT\n" +
            "\tb.st_id,b.st_name,a.c_name,b.st_pay,b.st_need,b.st_duration\n" +
            "FROM\n" +
            "\tt_company AS a\n" +
            "\tINNER JOIN\n" +
            "\tt_station AS b\n" +
            "\tON \n" +
            "\t\ta.c_id = b.c_id"+
            "\tLIMIT ?1,?2")
    List<Object[]> listAll(int start, int end);

    // 通过公司名 模糊查询
    @Query(nativeQuery = true , value = "SELECT\n" +
            "\tb.st_id,b.st_name,a.c_name,b.st_pay,b.st_need,b.st_duration\n" +
            "FROM\n" +
            "\tt_company AS a\n" +
            "\tINNER JOIN\n" +
            "\tt_station AS b\n" +
            "\tON \n" +
            "\t\ta.c_id = b.c_id" +
            "\tWHERE" +
            "\ta.c_name like %?1%" +
            "\tLIMIT ?2,?3")
    List<Object[]> searchCompanies(String companyName, int start, int end);

    //杨修伟部分
    //根据公司名查询
    @Query( value = "select * from t_company where c_name = #{c_name}",nativeQuery = true)
    List<Object[]> findByCName(String cName);

    //通过id删除公司
    @Query(value = "delete from t_company where c_id = ?1",nativeQuery = true)
    int deleteByid(Long cId);
}
