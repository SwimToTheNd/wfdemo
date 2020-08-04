package com.swimtothend.activiti.act;

import com.swimtothend.activiti.dao.LeaveInfoMapper;
import com.swimtothend.activiti.entity.LeaveInfo;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * create by BloodFly at 2019/3/21
 */
@Service
public class LeaveService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private LeaveInfoMapper leaveInfoMapper;
    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    //#{leaveService.updateStatus(execution,"ing")}
    //更新表单状态
    public void updateStatus(DelegateExecution execution, String status) {
        // 获取业务id
        String bizId = execution.getProcessInstanceBusinessKey();
        System.out.println("业务表[" + bizId + "]更新单据状态, status: " + status);
        // 根据业务id获取请假单据
        LeaveInfo leaveInfo = leaveInfoMapper.selectByPrimaryKey(bizId);
        leaveInfo.setStatus(status);
        leaveInfoMapper.updateByPrimaryKey(leaveInfo);
    }

    //${leaveService.findProjectManager(execution)}
    //查找项目经理
    public List<String> findProjectManager(DelegateExecution execution) {
        return Arrays.asList("pm1", "pm2");
    }

    //${leaveService.findHrManager(execution)}
    //查找人事经理
    public List<String> findHrManager(DelegateExecution execution) {
        return Arrays.asList("hr1", "hr2");
    }

    // 启动流程
    public ProcessInstance startProcesses(String bizId) {
        ProcessInstance leaveProcess = runtimeService.startProcessInstanceByKey("leaveProcess", bizId);
        System.out.println("流程启动成功，流程id: " + leaveProcess.getId());
        return leaveProcess;

    }

    // 查询待办
    public List<Task> findTaskByUserId(String userId) {
        // 获取指定流程下某用户的所有任务
        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("leaveProcess").taskCandidateOrAssigned(userId).list();
        return tasks;
    }


    // 审核任务
    public void complateTask(String taskId, String userId, String audit) {
        // 获取流程实例
        taskService.claim(taskId, userId);

        Map<String, Object> vars = new HashMap<>();
        vars.put("audit", audit);
        taskService.complete(taskId, vars);
    }

    /**
     * 根据流程实例id查询流程实例
     *
     * @param instanceId 流程实例id
     * @return 流程实例
     */
    public ProcessInstance findProcessInstance(String instanceId) {
        return runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
    }

    /**
     * 生成流程图图片
     *
     * @param processInstanceId
     */
    public void queryProcessImg(String processInstanceId) throws IOException {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        File file = new File("leaveProcess.png");
        if (!file.exists()) file.createNewFile();
        try (InputStream is = repositoryService.getProcessDiagram(historicProcessInstance.getProcessDefinitionId());
             FileOutputStream fos = new FileOutputStream(file);) {
            BufferedImage bi = ImageIO.read(is);
            ImageIO.write(bi, "png", fos);
            System.out.println("流程图图片生成成功");
        }
    }

    /**
     * 根据流程实例id,流程图高亮显示
     *
     * @param processInstanceId
     */
    public void queryProcessHighLighted(String processInstanceId) {
        // 获取历史流程实例
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        // 获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        ProcessDiagramGenerator processDiagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

        // 高亮结点id集合
        List<HistoricActivityInstance> activityInstances = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();

        // 高亮线程id集合
        // 配置字体
    }


}
