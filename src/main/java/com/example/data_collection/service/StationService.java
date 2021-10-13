package com.example.data_collection.service;

import com.example.data_collection.entity.Station;
import com.example.data_collection.result.ResponseResult;

public interface StationService {
    ResponseResult findAllStationAndCompany();

    ResponseResult findStationByCname(String cName);

    ResponseResult insertStation(Station station);

    ResponseResult updateStation(Station station);

}
