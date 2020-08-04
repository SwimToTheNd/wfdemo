package com.swimtothend.activiti.service;

import com.swimtothend.activiti.entity.LeaveInfo;

import java.util.List;

/**
 * create by BloodFly at 2019/3/21
 */
public interface ILeaveService {
    /**
     * 新增一个请假申请
     *
     * @param msg
     * @return
     */
    boolean addLeaveInfo(String msg);

    /**
     * 查询代办
     *
     * @param userId
     * @return
     */
    List<LeaveInfo> queryTaskByUserId(String userId);

    /**
     * 完成审核
     *
     * @param userId
     * @param taskId
     * @param audit
     */
    void completeTask(String userId, String taskId, String audit);
}
