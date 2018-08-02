package com.rbc.activiti.service;

import com.rbc.activiti.domain.ActivitiDO;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


public interface ActTaskService {
    List<ActivitiDO> listTodo(ActivitiDO act);

    void complete(String taskId, String procInsId, String comment, String title, Map<String, Object> vars);

    Task complete(String taskId,Map<String,Object> vars);

    Task startProcess(String procDefKey, String businessTable, String businessId, String title, Map<String, Object> vars);

    String getFormKey(String procDefId, String taskDefKey);

    InputStream tracePhoto(String processDefinitionId, String executionId);

    List<HistoricTaskInstance> findHistoryTask(List<String> groupIds);

    void claim(String taskId, String userName);
}
