package com.rbc.biz.controller;

import com.rbc.activiti.config.ActivitiConstant;
import com.rbc.activiti.service.impl.ActTaskServiceImpl;
import com.rbc.activiti.utils.ActivitiUtils;
import com.rbc.biz.domain.OptionsDO;
import com.rbc.biz.domain.PopValve104DO;
import com.rbc.biz.domain.TaskInfoDO;
import com.rbc.biz.service.OptionsService;
import com.rbc.biz.service.TaskInfoService;
import com.rbc.common.config.DateConverConfig;
import com.rbc.common.controller.BaseController;
import com.rbc.common.utils.*;
import com.rbc.system.domain.UserDO;
import com.rbc.system.service.UserService;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
 
@Controller
@RequestMapping("/biz/taskInfo")
public class TaskInfoController extends BaseController {
	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	UserService userService;
	@Autowired
	OptionsService optionsService;
	@Autowired
	DateConverConfig dateConverConfig;
	@Autowired
	private ActivitiUtils activitiUtils;
	@Autowired
	private ActTaskServiceImpl actTaskService;
	@GetMapping()
	@RequiresPermissions("biz:taskInfo:taskInfo")
	String TaskInfo(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/taskInfo/taskInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:taskInfo:taskInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TaskInfoDO> taskInfoList = taskInfoService.list(query);
		int total = taskInfoService.count(query);
		PageUtils pageUtils = new PageUtils(taskInfoList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listNew")
	@RequiresPermissions("biz:taskInfo:taskInfo")
	public PageUtils listNew(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<TaskInfoDO> taskInfoList = taskInfoService.list(query);
		int total = taskInfoService.count(query);
		PageUtils pageUtils = new PageUtils(taskInfoList, total);
		return pageUtils;
	}
	@GetMapping("/add")
	@RequiresPermissions("biz:taskInfo:add")
	String add(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		Map<String,Object> map=new HashMap();
		map.put("optionType","task");
		map.put("isvaliable",1);
		List<OptionsDO> optList=optionsService.list(map);
		model.addAttribute("optionList",optList);
	    return "biz/taskInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:taskInfo:edit")
	String edit(@PathVariable("id") Long id,Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		Map<String,Object> map=new HashMap();
		map.put("optionType","task");
		map.put("isvaliable",1);
		List<OptionsDO> optList=optionsService.list(map);
		model.addAttribute("optionList",optList);
		TaskInfoDO taskInfo = taskInfoService.get(id);
		model.addAttribute("taskInfo", taskInfo);
	    return "biz/taskInfo/edit";
	}

	@GetMapping("/look/{id}")
	String look(@PathVariable("id") Long id,Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		Map<String,Object> map=new HashMap();
		map.put("optionType","task");
		map.put("isvaliable",1);
		List<OptionsDO> optList=optionsService.list(map);
		model.addAttribute("optionList",optList);
		TaskInfoDO taskInfo = taskInfoService.get(id);
		model.addAttribute("taskInfo", taskInfo);
		return "biz/taskInfo/look";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:taskInfo:add")
	public R save( TaskInfoDO taskInfo){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date day=new Date();
		String dateStr=sdf.format(day);
		day=dateConverConfig.stringDateConvert().convert(dateStr);
		taskInfo.setCreateTime(day);
		taskInfo.setUpdateTime(day);
		if(taskInfoService.save(taskInfo)>0){
			String taskId = taskInfo.getTaskId();
			if(StringUtils.isNotBlank(taskId)) {
				actTaskService.claim(taskId, getUsername());
			}
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:taskInfo:edit")
	public R update( TaskInfoDO taskInfo){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date day=new Date();
		String dateStr=sdf.format(day);
		day=dateConverConfig.stringDateConvert().convert(dateStr);
		taskInfo.setUpdateTime(day);
		if(taskInfoService.update(taskInfo)>0){
			String taskId = taskInfo.getTaskId();
			if(StringUtils.isNotBlank(taskId)) {
				actTaskService.claim(taskId, getUsername());
			}
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:taskInfo:remove")
	public R remove( Long id){
		if(taskInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:taskInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		taskInfoService.batchRemove(ids);
		return R.ok();
	}
	@GetMapping("/listWorker")
	@ResponseBody
	PageUtils listWorker(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}
	@PostMapping( "/getOptionName")
	@ResponseBody
	public R getOptionName(@RequestParam("optionValue") String optionValue){
		Map<String,Object> map=new HashMap();
		map.put("optionType","task");
		map.put("isvaliable",1);
		map.put("optionValue",optionValue);
		List<OptionsDO> optList=optionsService.list(map);
		Map<String,Object> Rmap=new HashMap();
		if(optList.size()>0){
			Rmap.put("code", 0);
			Rmap.put("msg", "操作成功!");
			Rmap.put("optionName",optList.get(0).getOptionName());
		}else{
			Rmap.put("code", 1);
			Rmap.put("msg", "取值失败!");
		}
		return R.ok(Rmap);

	}

	//工作流开始页面
	@GetMapping("/form")
	String form(Model model) {
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/taskInfo/add";
	}

	//工作流编辑页面
	@GetMapping("/form/{taskId}")
	String form(@PathVariable("taskId") String taskId, Model model) {
		Task task = activitiUtils.getTaskByTaskId(taskId);
		TaskInfoDO taskInfo = taskInfoService.get(Long.valueOf(activitiUtils.getBusinessKeyByTask(task)));
		taskInfo.setTaskId(taskId);
		model.addAttribute("taskInfo", taskInfo);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		Map<String,Object> map=new HashMap();
		map.put("optionType","task");
		map.put("isvaliable",1);
		List<OptionsDO> optList=optionsService.list(map);
		model.addAttribute("optionList",optList);
		return "biz/taskInfo/edit";
	}

	//工作流流转（签字）
	@ResponseBody
	@RequestMapping("/sign")
	public R sign(TaskInfoDO taskInfo){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date day=new Date();
		String dateStr=sdf.format(day);
		day=dateConverConfig.stringDateConvert().convert(dateStr);
		if(taskInfo.getId() == null) {
			taskInfo.setCreateTime(day);
			taskInfoService.save(taskInfo);
		}
		if(StringUtils.isBlank(taskInfo.getTaskId())) {
			taskInfo.setDistribTime(day);
			HashMap map = new HashMap<>();
			map.put("processName","任务发布");
			Map<String,Object> fixTaskMap = new HashMap();
			fixTaskMap.put("optionType","task");
			fixTaskMap.put("isvaliable",1);
			fixTaskMap.put("optionValue",taskInfo.getFixTask());
			List<OptionsDO> optList = optionsService.list(fixTaskMap);
			Map params = new HashMap();
			params.put("taskInfoId", taskInfo.getId());
			params.put("fixTaskName", optList.get(0).getOptionName());
			map.put("params", params);
			map.put("processForm","/biz/taskInfo/form");
			map.put("fixWorkerNo",taskInfo.getFixWorkerNo());
			map.put("createTime",DateUtils.format(taskInfo.getCreateTime()));
			Task task = actTaskService.startProcess(ActivitiConstant.ACTIVITI_TASK[0],ActivitiConstant.ACTIVITI_TASK[1],taskInfo.getId().toString(),null,map);
			taskInfo.setTaskId(task.getId());
			taskInfo.setTaskName(task.getName());
			taskInfo.setProcessInstanceId(task.getProcessInstanceId());
		}
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("pass",  taskInfo.getTaskPass());
		vars.put("fixWorkerNo",taskInfo.getFixWorkerNo());
		vars.put("gangmasterNo",taskInfo.getGangmasterNo());
		Task task = actTaskService.complete(taskInfo.getTaskId(),vars);
		if(task != null) {
			taskInfo.setTaskId(task.getId());
			taskInfo.setTaskName(task.getName());
		}
		taskInfo.setUpdateTime(day);
		taskInfoService.update(taskInfo);
		return R.ok();
	}
}
