package com.example.data_collection.service.impl;

import com.example.data_collection.dao.StationDao;
import com.example.data_collection.entity.Station;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {
    //注入接口
    @Autowired
    private StationDao stationDao;

    /**
     * 查找岗位信息以及对应公司名
     * @return
     */
    @Override
    public ResponseResult findAllStationAndCompany() {
        return ResponseResult.SUCCESS("查找成功").setData(stationDao.findAllStationAndCompany());
    }

    /**
     * 通过公司名查找岗位信息
     * @param cName
     * @return
     */
    @Override
    public ResponseResult findStationByCname(String cName) {
        return ResponseResult.SUCCESS("查找成功").setData(stationDao.findStationByCname(cName));
    }

    /**
     * 添加岗位
     * @param station
     * @return
     */
    @Override
    public ResponseResult insertStation(Station station) {
        return ResponseResult.SUCCESS("添加成功").setData(stationDao.save(station));
    }

    /**
     * 修改岗位
     * @param station
     * @return
     */
    @Override
    public ResponseResult updateStation(Station station) {
        return ResponseResult.SUCCESS("修改成功").setData(stationDao.save(station));
    }

    /**
     * 通过id删除岗位信息
     * @param stId
     * @return
     */
    @Override
    public ResponseResult deleteStudent(Long stId) {
        return ResponseResult.SUCCESS("删除成功").setData(stationDao.deleteByid(stId));
    }
}
