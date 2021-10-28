package com.example.data_collection.controller.back;

import com.example.data_collection.entity.Station;
import com.example.data_collection.result.ResponseResult;
import com.example.data_collection.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@PreAuthorize("@permission.admin()")
@RequestMapping("/back")
public class StationController {
    //注入
    @Autowired
    private StationService stationService;

    /**
     * 查找所有岗位信息
     * @param size 数量
     * @param page 页码
     * @return 返回结果
     */
    @RequestMapping("/findAllStation/{size}/{page}")
    public ResponseResult findAllStationAndCompany(@PathVariable("size") int size ,
                                                   @PathVariable("page") int page){
        return stationService.findAllStation(page , size);
    }

    /**
     * 通过岗位名字查询岗位
     * @param size 数量
     * @param page 页码
     * @param stationName 岗位名
     * @return 返回结果
     */
    @GetMapping("/findStationByName/{size}/{page}")
    public ResponseResult findStationByName(@PathVariable("size") int size,
                                            @PathVariable("page") int page,
                                            @PathParam("stationName") String stationName){
        return stationService.findStationByName(page, size, stationName);
    }

    /**
     * 通过公司名字查询岗位
     * @param size 数量
     * @param page 页码
     * @param companyName 公司名
     * @return 返回结果
     */
    @GetMapping("/findStationByCompany/{size}/{page}")
    public ResponseResult findStationByCompany(@PathVariable("size") int size,
                                            @PathVariable("page") int page,
                                            @PathParam("companyName") String companyName){
        return stationService.findStationByCompany(page, size, companyName);
    }

    /**
     * 添加岗位信息
     * @param station 岗位信息
     * @return 返回结果
     */
    @RequestMapping("/addStation")
    public ResponseResult addStation(Station station){
        return stationService.addStation(station);
    }

    /**
     * 修改岗位信息
     * @param station 岗位信息
     * @return 返回结果
     */
    @RequestMapping("/editStation")
    public ResponseResult editStation(Station station){
        return stationService.editStation(station);
    }

    /**
     * 通过id删除公司信息
     * @param stId 岗位ID
     * @return 返回结果
     */
    @DeleteMapping("/deleteStation/{stId}")
    public ResponseResult deleteStudent(@PathVariable("stId") Long stId){
        return stationService.deleteStation(stId);
    }

    /**
     * 批量添加岗位
     * @param stations 岗位列表
     * @return 返回结果
     */
    @PostMapping("/addStations")
    public ResponseResult addStations(List<Station> stations){
        return stationService.addStations(stations);
    }

    /**
     * 批量删除岗位
     * @param ids 岗位ID列表
     * @return 返回结果
     */
    @DeleteMapping("/deleteStations")
    public ResponseResult deleteStations(List<Long> ids){
        return stationService.deleteStations(ids);
    }
}
