package com.example.data_collection;

import com.example.data_collection.utils.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataCollectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataCollectionApplication.class, args);
    }

    @Bean
    public RedisUtil RedisUtil(){
        return new RedisUtil();
    }
}
