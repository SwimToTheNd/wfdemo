package com.swimtothend.camunda.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by BloodFly at 2019/3/26
 */
@RestController
public class CamundaController {

    @GetMapping("/hello")
    public Object sayHello() {
        return "hello Camunda!";
    }
}
