package com.example.data_collection.service;

import com.example.data_collection.result.ResponseResult;

public interface ListService {
    // 根据公司名 模糊查询
    ResponseResult searchCompanies(String companyName, int page, int size);
    // 根据岗位名 模糊查询
    ResponseResult searchStation(String stationName, int page, int size);
    // 列出所有数据
    ResponseResult listAll(int page, int size);
}
