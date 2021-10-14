package com.example.data_collection.service;

import com.example.data_collection.entity.Station;
import com.example.data_collection.result.ResponseResult;

public interface StationService {
    //查询岗位信息以及公司名
    ResponseResult findAllStationAndCompany();
    //通过公司名查询岗位信息
    ResponseResult findStationByCname(String cName);
    //添加岗位信息
    ResponseResult insertStation(Station station);
    //修改岗位信息
    ResponseResult updateStation(Station station);
    //通过id删除岗位信息
    ResponseResult deleteStudent(Long stId);
}
