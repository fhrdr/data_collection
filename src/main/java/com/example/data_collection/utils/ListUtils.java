package com.example.data_collection.utils;

import com.example.data_collection.entity.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// lists工具类
public class ListUtils {
    // 把收到的 Object 转换为 Lists 类
    public static HashMap<String, Object> change(List<Object[]> objects, int page, int size){
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
}
