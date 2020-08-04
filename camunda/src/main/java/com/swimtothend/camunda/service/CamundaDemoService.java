package com.swimtothend.camunda.service;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * create by BloodFly at 2019/3/26
 */
@Service
public class CamundaDemoService {

    @Autowired
    private RuntimeService runtimeService;

    @EventListener
    public void processPostDeploy (PostDeployEvent event) {
//        runtimeService.startProcessInstanceByKey("payment-retrieval");
    }
}
