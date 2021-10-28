package com.example.data_collection.service;

import com.example.data_collection.entity.Station;
import com.example.data_collection.result.ResponseResult;

import java.util.List;

public interface StationService {
    // 查询所有岗位信息
    ResponseResult findAllStation(int page, int size);
    // 通过岗位名查询岗位信息
    ResponseResult findStationByName(int page , int size , String stationName);
    // 通过公司名查询岗位信息
    ResponseResult findStationByCompany(int page, int size, String companyName);
    // 添加岗位信息
    ResponseResult addStation(Station station);
    // 批量添加岗位信息
    ResponseResult addStations(List<Station> stations);
    // 修改岗位信息
    ResponseResult editStation(Station station);
    // 通过id删除岗位信息
    ResponseResult deleteStation(Long stId);
    // 批量删除岗位信息
    ResponseResult deleteStations(List<Long> ids);
}
