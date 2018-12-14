package com.rbc.activiti.controller;


import com.rbc.activiti.service.ActTaskService;
import com.rbc.activiti.vo.ProcessVO;
import com.rbc.activiti.vo.TaskVO;
import com.rbc.biz.domain.PopValve104DO;
import com.rbc.common.controller.BaseController;
import com.rbc.common.utils.DateUtils;
import com.rbc.common.utils.PageUtils;
import com.rbc.common.utils.StringUtils;
import com.rbc.system.service.RoleService;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RequestMapping("activiti/task")
@RestController
public class TaskController  extends BaseController {

    @Autowired
    RepositoryService repositoryService;
    @Autowired
    FormService formService;
    @Autowired
    TaskService taskService;
    @Autowired
    ActTaskService actTaskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RoleService roleService;

    @GetMapping("goto")
    public ModelAndView gotoTask(){
        return new ModelAndView("act/task/gotoTask");
    }

    @GetMapping("/gotoList")
    PageUtils list(int offset, int limit) {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().listPage(offset, limit);
        int count = (int) repositoryService.createProcessDefinitionQuery().count();
        List<Object> list = new ArrayList<>();
        for(ProcessDefinition processDefinition: processDefinitions){
            list.add(new ProcessVO(processDefinition));
        }

        PageUtils pageUtils = new PageUtils(list, count);
        return pageUtils;
    }

    @GetMapping("/form/{procDefId}")
    public void startForm(@PathVariable("procDefId") String procDefId  ,HttpServletResponse response) throws IOException {
        String formKey = actTaskService.getFormKey(procDefId, null);
        response.sendRedirect(formKey);
    }

    @GetMapping("/form/{procDefId}/{taskId}")
    public void form(@PathVariable("procDefId") String procDefId,@PathVariable("taskId") String taskId ,HttpServletResponse response) throws IOException {
        // 获取流程XML上的表单KEY
        String formKey = (String)taskService.getVariable(taskId,"processForm");
        if(StringUtils.isBlank(formKey)) {
            formKey = actTaskService.getFormKey(procDefId, taskId);
        }
        response.sendRedirect(formKey+"/"+taskId);
    }

    @GetMapping("/todo")
    ModelAndView todo(){

        return new ModelAndView("act/task/todoTask");
    }

    @GetMapping("/sp")
    ModelAndView sp(){
        return new ModelAndView("act/task/spTask");
    }
    /**
     * 获取个人任务列表
     */
    @GetMapping("/taskList/{taskType}")
    List<TaskVO> taskList(@PathVariable("taskType") String taskType,@RequestParam Map<String, Object> params){
        String CurProcessName="";
        if(taskType.equals("assistF8")){
            CurProcessName="F8辅助阀";
        } else if(taskType.equals("mainF8")){
            CurProcessName="F8主阀";
        } else if(taskType.equals("main104")){
            CurProcessName="104主阀";
        } else if(taskType.equals("pop104")){
            CurProcessName="104紧急阀";
        } else if(taskType.equals("taskInfo")){
            CurProcessName="任务发布";
        } else if(taskType.equals("toolInspection")){
            CurProcessName="检修设备检视";
        } else if(taskType.equals("testTool")){
            CurProcessName="试验设备检视";
        }
        String  popValueTask =(params.containsKey("popValueTask")&&!params.get("popValueTask").toString().equals("")?params.get("popValueTask").toString():null);

        Date createTimeTask =null;

        if(params.containsKey("createTimeTask")&&params.get("createTimeTask").toString().equals("")){
            createTimeTask= DateUtils.strToDate(DateUtils.dateToStr(new Date()));
        }else if(params.containsKey("createTimeTask")&&!params.get("createTimeTask").toString().equals("")){
            createTimeTask= DateUtils.strToDate(params.get("createTimeTask").toString());
        }


        List<Task> tasks = taskService.createTaskQuery().taskAssignee(getUsername()).orderByTaskCreateTime().asc().list();
        List<String> groupIds = roleService.getGroupIds(getUserId());
        List<Task> taskGroupList = taskService.createTaskQuery().taskCandidateGroupIn(groupIds).orderByTaskCreateTime().asc().list();
        tasks.addAll(taskGroupList);
        List<TaskVO> taskVOS =  new ArrayList<>();
        if(popValueTask!=null){
            for(Task task : tasks) {
                TaskVO taskVO = new TaskVO(task);
                String dateStr= (String) taskService.getVariable(task.getId(),"createTime");
                //Date taskCreatDate = DateUtils.strToDate((String) taskService.getVariable(task.getId(), "createTime"));
                if (!CurProcessName.equals((String) taskService.getVariable(task.getId(), "processName"))) {
                    continue;
                }
                if(dateStr!=null){
                    Date taskCreatDate = DateUtils.strToDate(dateStr);
                    if(createTimeTask!=null&&taskCreatDate.compareTo(createTimeTask)!=0){continue;}
                }else{continue;}
                if ( popValueTask.equals((String) taskService.getVariable(task.getId(), "processNumber"))) {
                    taskVO.setProcessName((String) taskService.getVariable(task.getId(), "processName"));
                    taskVO.setProcessNumber((String) taskService.getVariable(task.getId(), "processNumber"));
                    taskVO.setParams((Map) taskService.getVariable(task.getId(), "params"));
                    taskVO.setCreateTime((String) taskService.getVariable(task.getId(), "createTime"));
                    taskVOS.add(taskVO);
                    break;
                }
            }
        }else{
            for(Task task : tasks){
                TaskVO taskVO = new TaskVO(task);
                String dateStr= (String) taskService.getVariable(task.getId(),"createTime");
                if(!CurProcessName.equals((String)taskService.getVariable(task.getId(),"processName"))){continue;}
                if(dateStr!=null){
                    Date taskCreatDate = DateUtils.strToDate(dateStr);
                    if(createTimeTask!=null&&taskCreatDate.compareTo(createTimeTask)!=0){continue;}
                }else{continue;}
                taskVO.setProcessName((String)taskService.getVariable(task.getId(),"processName"));
                taskVO.setProcessNumber((String)taskService.getVariable(task.getId(),"processNumber"));
                taskVO.setParams((Map) taskService.getVariable(task.getId(),"params"));
                taskVO.setCreateTime((String) taskService.getVariable(task.getId(), "createTime"));
                taskVOS.add(taskVO);

            }
        }

        return taskVOS;
    }


    /**
     * 读取带跟踪的图片
     */
    @RequestMapping(value = "/trace/photo/{procDefId}/{execId}")
    public void tracePhoto(@PathVariable("procDefId") String procDefId, @PathVariable("execId") String execId, HttpServletResponse response) throws Exception {
        InputStream imageStream = actTaskService.tracePhoto(procDefId, execId);

        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }


}
