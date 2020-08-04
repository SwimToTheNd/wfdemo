package com.swimtothend.activiti.service.impl;

import com.swimtothend.activiti.act.LeaveService;
import com.swimtothend.activiti.service.ILeaveService;
import com.swimtothend.activiti.dao.LeaveInfoMapper;
import com.swimtothend.activiti.entity.LeaveInfo;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * create by BloodFly at 2019/3/21
 */
@Service
public class LeaveServiceImpl implements ILeaveService {

    @Autowired
    private LeaveService leaveService;
    @Autowired
    private LeaveInfoMapper leaveInfoMapper;
    @Autowired
    private RuntimeService runtimeService;

    @Override
    public boolean addLeaveInfo(String msg) {
        LeaveInfo leaveInfo = new LeaveInfo();
        String bizId = UUID.randomUUID().toString();
        leaveInfo.setId(bizId);
        leaveInfo.setLeaveMsg(msg);
        leaveInfoMapper.insertSelective(leaveInfo);
        // 启动流程时要和业务id关联
        ProcessInstance processInstance = leaveService.startProcesses(bizId);
        return processInstance != null;
    }

    @Override
    public List<LeaveInfo> queryTaskByUserId(String userId) {
        List<LeaveInfo> leaveInfos = new ArrayList<>();
        // 查询用户的所有任务
        List<Task> tasks = leaveService.findTaskByUserId(userId);
        for (Task task : tasks) {
            // 根据任务获得流程实例id，然后根据流程实例id查询流程具体实例
            ProcessInstance processInstance = leaveService.findProcessInstance(task.getProcessInstanceId());
            // 根据流程实例获取业务id
            String bizId = processInstance.getBusinessKey();
            // 根据业务id查询单据
            LeaveInfo leaveInfo = leaveInfoMapper.selectByPrimaryKey(bizId);
            // 设置任务id
            leaveInfo.setTaskId(task.getId());
            leaveInfos.add(leaveInfo);
        }
        return leaveInfos;
    }

    @Override
    public void completeTask(String userId, String taskId, String audit) {
        leaveService.complateTask(taskId, userId, audit);
    }
}
