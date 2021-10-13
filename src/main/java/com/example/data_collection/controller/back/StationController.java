package com.example.data_collection.controller.back;

import com.example.data_collection.entity.Station;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/back/station")
public class StationController {
    //注入
    @Resource
    private StationService stationService;

    /**
     * 查找所有岗位信息
     * @return
     */
    @RequestMapping("/findAll")
    public ResponseResult findAllStationAndCompany(){
        return stationService.findAllStationAndCompany();
    }

    /**
     * 通过公司名查询岗位信息
     * @param sName
     * @return
     */
    @RequestMapping("/findByCname")
    public ResponseResult findStationByCname(String sName){
        return stationService.findStationByCname(sName);
    }

    /**
     * 添加岗位信息
     * @param station
     * @return
     */
    @RequestMapping("/add")
    public ResponseResult addStation(Station station){
        return stationService.insertStation(station);
    }

    /**
     * 修改岗位信息
     * @param station
     * @return
     */
    @RequestMapping("/update")
    public ResponseResult updateStation(Station station){
        return stationService.updateStation(station);
    }

    /**
     * 通过id删除公司信息
     * @param stId
     * @return
     */
    @RequestMapping("/delete/{st_id}")
    public ResponseResult deleteStudent(@PathVariable("stId") Long stId){
        return stationService.deleteStudent(stId);
    }
}
