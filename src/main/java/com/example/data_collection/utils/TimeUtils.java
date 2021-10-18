package com.example.data_collection.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    // 比较输入的时间，格式为：yyyy-MM-dd
    public static Boolean compareTime(String start, String end){
        try {
            // 生成现在的时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String[] now = simpleDateFormat.format(new Date()).split("-");
            String[] starts = start.split("-");
            String[] ends = end.split("-");
            // 判断时间
            if (Integer.parseInt(now[0])>=Integer.parseInt(starts[0]) && Integer.parseInt(now[0]) <= Integer.parseInt(ends[0])){
                if (Integer.parseInt(now[1])>=Integer.parseInt(starts[1]) && Integer.parseInt(now[1]) <= Integer.parseInt(ends[1])){
                    return Integer.parseInt(now[2]) >= Integer.parseInt(starts[2]) && Integer.parseInt(now[2]) <= Integer.parseInt(ends[2]);
                }else {
                    return false;
                }
            }else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }
}
