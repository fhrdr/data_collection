package com.example.data_collection.controller;

import com.example.data_collection.result.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    // 这是一个简单的项目测试
    @GetMapping("hello")
    ResponseResult hello(){
        String a ="c_id";
        System.out.println(a.compareTo("id"));
        return ResponseResult.SUCCESS("获取成功！").setData("hello");
    }
}
