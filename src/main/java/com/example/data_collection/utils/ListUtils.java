package com.example.data_collection.utils;

import com.example.data_collection.entity.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// lists工具类
public class ListUtils {
    // 把收到的 Object 转换为 Lists 类
    public static Map<String, Object> change(List<Object[]> objects, int page, int size){
        List<Lists> lists = new ArrayList<>();
        for (Object[] o : objects) {
            lists.add(new Lists(o[0],o[1],o[2],o[3],o[4],o[5],o[6],o[7],o[8]));
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("data", lists);
        result.put("page", page+1);
        result.put("size", size);
        result.put("num", lists.size());
        return result;
    }

    // 提取详细信息值
    public static Map<String, Object> getInfo(List<Object[]> objects){
        Object[] ob = objects.get(0);
        Map<String , Object> result = new HashMap<String, Object>();
        result.put("c_id", ob[0]);
        result.put("c_name" , ob[1]);
        result.put("c_people_num" , ob[2]);
        result.put("c_nature" , ob[3]);
        result.put("c_introduction" , ob[4]);
        result.put("c_address" , ob[5]);
        result.put("st_id" , ob[6]);
        result.put("st_name" , ob[7]);
        result.put("st_need" , ob[8]);
        result.put("st_describe" , ob[9]);
        result.put("st_pay" , ob[10]);
        result.put("st_duration" , ob[11]);
        result.put("st_start" , ob[12]);
        result.put("st_end" , ob[13]);
        result.put("st_contacts" , ob[14]);
        result.put("st_phone" , ob[15]);
        result.put("st_remarks" , ob[16]);
        result.put("station_num" , ob[17]);
        result.put("can_choose" , ob[18]);
        result.put("is_choose" , ob[19]);
        return result;
    }

    // 提取岗位信息
    public static List<Map<String , Object>> getStations(List<Object[]> objects){
        List<Map<String , Object>> result = new ArrayList<>();
        for (Object[] o : objects){
            Map<String , Object> map = new HashMap<>();
            map.put("stId" , o[0]);
            map.put("cId" , o[1]);
            map.put("stName" , o[2]);
            map.put("stNeed" , o[3]);
            map.put("stDescribe" , o[4]);
            map.put("stPay" , o[5]);
            map.put("stDuration" , o[6]);
            map.put("stStart" , o[7]);
            map.put("stEnd" , o[8]);
            map.put("stContacts" , o[9]);
            map.put("stPhone" , o[10]);
            map.put("stRemarks" , o[11]);
            map.put("cName" , o[12]);
            result.add(map);
        }
        return result;
    }
}
