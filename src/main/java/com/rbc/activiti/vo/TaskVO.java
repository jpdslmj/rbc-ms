package com.rbc.activiti.vo;

import org.activiti.engine.task.Task;

import java.util.Map;


public class TaskVO  {

    public TaskVO(Task task){

        this.setId(task.getId());
        this.setKey(task.getTaskDefinitionKey());
        this.setName(task.getName());
        this.setDescription(task.getDescription());
        this.setAssignee(task.getAssignee());
        this.setFormKey(task.getFormKey());
        this.setProcessId(task.getProcessInstanceId());
        this.setProcessDefinitionId(task.getProcessDefinitionId());
        this.setExecutionId(task.getExecutionId());
    }
    private  String id;
    private String name;
    private String key;
    private String description;
    private  String formKey;
    private  String assignee;
    private String processId;
    private String processDefinitionId;
    private String executionId;
    private String processName;
    private String processNumber;
    private String processForm;
    private Map params;
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(String processNumber) {
        this.processNumber = processNumber;
    }

    public String getProcessForm() {
        return processForm;
    }

    public void setProcessForm(String processForm) {
        this.processForm = processForm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }
}
