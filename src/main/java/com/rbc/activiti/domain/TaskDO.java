package com.rbc.activiti.domain;

import java.util.Map;


public class TaskDO {
    private String taskId;
    private String taskComment;
    private String taskPass;
    private String processInstanceId;
    private String taskName;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    private Map<String,Object> vars;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskComment() {
        return taskComment;
    }

    public void setTaskComment(String taskComment) {
        this.taskComment = taskComment;
    }

    public String getTaskPass() {
        return taskPass;
    }

    public void setTaskPass(String taskPass) {
        this.taskPass = taskPass;
    }

    public Map<String, Object> getVars() {
        return vars;
    }

    public void setVars(Map<String, Object> vars) {
        this.vars = vars;
    }


}
