package com.swimtothend.activiti.controller;

import com.swimtothend.activiti.entity.LeaveInfo;
import com.swimtothend.activiti.service.ILeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * create by BloodFly at 2019/3/21
 */
@RestController
public class LeaveController {

    @Autowired
    private ILeaveService iLeaveService;

    @GetMapping("/hello")
    public Object sayHello() {
        return "hello activiti";
    }

    /**
     * 请假申请
     *
     * @param msg
     * @return
     */
    @GetMapping("/addLeaveInfo")
    public Object addLeaveInfo(String msg) {
        iLeaveService.addLeaveInfo(msg);
        return "成功申请请假";
    }

    /**
     * 查询待办
     *
     * @param userId 用户id
     * @return 待办列表
     */
    @GetMapping("/queryTask")
    public Object queryTask(String userId) {
        List<LeaveInfo> leaveInfos = iLeaveService.queryTaskByUserId(userId);
        return leaveInfos;
    }


    /**
     * 完成待办
     *
     * @param userId 用户id
     * @param taskId 任务id
     * @param audit  审批结果
     * @return
     */
    @GetMapping("/completeTask")
    public Object completeTask(String userId, String taskId, String audit) {
        iLeaveService.completeTask(userId, taskId, audit);
        return "审批操作成功";
    }
}
