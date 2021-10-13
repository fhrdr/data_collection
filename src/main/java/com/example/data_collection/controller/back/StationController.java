package com.example.data_collection.controller.back;

import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/back/station")
public class StationController {
    @Resource
    private StationService stationService;

    @RequestMapping("/findAll")
    public ResponseResult findAllStationAndCompany(){
        return stationService.findAllStationAndCompany();
    }

    @RequestMapping("/findByCname")
    public ResponseResult findStationByCname(String sName){
        return stationService.findStationByCname(sName);
    }


}
