package com.example.data_collection.service.impl;

import com.example.data_collection.dao.StationDao;
import com.example.data_collection.entity.Station;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {
    @Autowired
    private StationDao stationDao;

    @Override
    public ResponseResult findAllStationAndCompany() {
        return ResponseResult.SUCCESS("查找成功").setData(stationDao.findAllStationAndCompany());
    }

    @Override
    public ResponseResult findStationByCname(String cName) {
        return ResponseResult.SUCCESS("查找成功").setData(stationDao.findStationByCname(cName));
    }

    @Override
    public ResponseResult insertStation(Station station) {
        return ResponseResult.SUCCESS("添加成功").setData(stationDao.save(station));
    }

    @Override
    public ResponseResult updateStation(Station station) {
        return ResponseResult.SUCCESS("修改成功").setData(stationDao.save(station));
    }
}
