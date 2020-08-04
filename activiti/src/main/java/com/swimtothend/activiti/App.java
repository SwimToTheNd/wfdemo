package com.swimtothend.activiti;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * create by BloodFly at 2019/3/21
 */
@SpringBootApplication
@MapperScan("com.swimtothend.activiti.dao")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
