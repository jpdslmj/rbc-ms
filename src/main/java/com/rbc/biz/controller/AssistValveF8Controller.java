package com.rbc.biz.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbc.activiti.config.ActivitiConstant;
import com.rbc.activiti.service.impl.ActTaskServiceImpl;
import com.rbc.activiti.utils.ActivitiUtils;
import com.rbc.biz.domain.ToolInspectionDO;
import com.rbc.biz.service.ToolInspectionService;
import com.rbc.common.controller.BaseController;
import com.rbc.common.utils.*;
import com.rbc.system.domain.UserDO;
import com.rbc.system.service.UserService;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rbc.biz.domain.AssistValveF8DO;
import com.rbc.biz.service.AssistValveF8Service;

/**
 * F8辅助阀信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-23 00:13:25
 */
 
@Controller
@RequestMapping("/biz/assistValveF8")
public class AssistValveF8Controller extends BaseController {
	@Autowired
	private AssistValveF8Service assistValveF8Service;
	@Autowired
	private UserService userService;
	@Autowired
	private ActivitiUtils activitiUtils;
	@Autowired
	private ActTaskServiceImpl actTaskService;
	@GetMapping()
	@RequiresPermissions("biz:assistValveF8:assistValveF8")
	String AssistValveF8(Model model){
        UserDO userDO  = userService.get(getUserId());
        model.addAttribute("user",userDO);
	    return "biz/assistValveF8/assistValveF8";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:assistValveF8:assistValveF8")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AssistValveF8DO> assistValveF8List = assistValveF8Service.list(query);
		int total = assistValveF8Service.count(query);
		PageUtils pageUtils = new PageUtils(assistValveF8List, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listNew")
	@RequiresPermissions("biz:assistValveF8:assistValveF8")
	public PageUtils listNew(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<AssistValveF8DO> assistValveF8List = assistValveF8Service.list(query);
		int total = assistValveF8Service.count(query);
		PageUtils pageUtils = new PageUtils(assistValveF8List, total);
		return pageUtils;
	}


	@GetMapping("/add")
	@RequiresPermissions("biz:assistValveF8:add")
	String add(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/assistValveF8/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:assistValveF8:edit")
	String edit(@PathVariable("id") Long id,Model model){
		AssistValveF8DO assistValveF8 = assistValveF8Service.get(id);
		model.addAttribute("assistValveF8", assistValveF8);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/assistValveF8/edit";
	}
	@GetMapping("/look/{id}")
	String look(@PathVariable("id") Long id,Model model){
		AssistValveF8DO assistValveF8 = assistValveF8Service.get(id);
		model.addAttribute("assistValveF8", assistValveF8);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/assistValveF8/look";
	}

	//工作流开始页面
	@GetMapping("/form")
	String form(Model model) {
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/assistValveF8/add";
	}

	//工作流编辑页面
	@GetMapping("/form/{taskId}")
	String form(@PathVariable("taskId") String taskId, Model model) {
		Task task = activitiUtils.getTaskByTaskId(taskId);
		AssistValveF8DO assistValveF8 = assistValveF8Service.get(Long.valueOf(activitiUtils.getBusinessKeyByTask(task)));
		assistValveF8.setTaskId(taskId);
		model.addAttribute("assistValveF8", assistValveF8);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/assistValveF8/edit";
	}

	//工作流流转（签字）
	@ResponseBody
	@RequestMapping("/sign")
	public R sign(AssistValveF8DO assistValveF8){
		if(assistValveF8.getId() == null) {
			assistValveF8Service.save(assistValveF8);
		}
		if(StringUtils.isBlank(assistValveF8.getTaskId())) {
			HashMap map = new HashMap<>();
			map.put("processName","F8辅助阀");
			map.put("processNumber",assistValveF8.getAssistValue());
			map.put("processForm","/biz/assistValveF8/form");
			Task task = actTaskService.startProcess(ActivitiConstant.ACTIVITI_PROCESS104[0],ActivitiConstant.ACTIVITI_PROCESS104[1],assistValveF8.getId().toString(),null,map);
			assistValveF8.setTaskId(task.getId());
			assistValveF8.setTaskName(task.getName());
			assistValveF8.setProcessInstanceId(task.getProcessInstanceId());
		}
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("pass",  assistValveF8.getTaskPass());
		Task task = actTaskService.complete(assistValveF8.getTaskId(),vars);
		if(task != null) {
			assistValveF8.setTaskId(task.getId());
			assistValveF8.setTaskName(task.getName());
		}
		assistValveF8Service.update(assistValveF8);
		return R.ok();
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:assistValveF8:add")
	public R save( AssistValveF8DO assistValveF8){
		if(assistValveF8Service.save(assistValveF8)>0){
			String taskId = assistValveF8.getTaskId();
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
	@RequiresPermissions("biz:assistValveF8:edit")
	public R update( AssistValveF8DO assistValveF8){
		String taskId = assistValveF8.getTaskId();
		if(StringUtils.isNotBlank(taskId)) {
			actTaskService.claim(taskId, getUsername());
		}
		assistValveF8Service.update(assistValveF8);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:assistValveF8:remove")
	public R remove( Long id){
		if(assistValveF8Service.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:assistValveF8:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		assistValveF8Service.batchRemove(ids);
		return R.ok();
	}
	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !assistValveF8Service.exit(params);
	}

	@Autowired
	ToolInspectionService toolInspectionService;
	@PostMapping("/workPermission")
	@ResponseBody
	public R workPermission(@RequestParam Map<String, Object> params) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date day=new Date();
		String dateStr=sdf.format(day);
		UserDO userDO  = userService.get(getUserId());
		params.put("fixWorkerNo",userDO.getUsername());
		params.put("createTime",dateStr);
		List<ToolInspectionDO> toolinspection= toolInspectionService.list(params);
        if(toolinspection!=null&&toolinspection.size()>0){
			Integer gangmasterAudit= toolinspection.get(0).getGangmasterAudit();
			if(gangmasterAudit!=null&&gangmasterAudit==1){
				return R.ok();
			}
		}
		return R.error();
	}

}
