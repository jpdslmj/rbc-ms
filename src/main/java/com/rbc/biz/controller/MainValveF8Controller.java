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

import com.rbc.biz.domain.MainValveF8DO;
import com.rbc.biz.service.MainValveF8Service;

/**
 * F8主阀信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-23 00:13:25
 */
 
@Controller
@RequestMapping("/biz/mainValveF8")
public class MainValveF8Controller extends BaseController {
	@Autowired
	private MainValveF8Service mainValveF8Service;
	@Autowired
	private UserService userService;
	@Autowired
	private ActivitiUtils activitiUtils;
	@Autowired
	private ActTaskServiceImpl actTaskService;
	@GetMapping()
	@RequiresPermissions("biz:mainValveF8:mainValveF8")
	String MainValveF8(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/mainValveF8/mainValveF8";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:mainValveF8:mainValveF8")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MainValveF8DO> mainValveF8List = mainValveF8Service.list(query);
		int total = mainValveF8Service.count(query);
		PageUtils pageUtils = new PageUtils(mainValveF8List, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listNew")
	@RequiresPermissions("biz:mainValveF8:mainValveF8")
	public PageUtils listNew(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<MainValveF8DO> assistValveF8List = mainValveF8Service.list(query);
		int total = mainValveF8Service.count(query);
		PageUtils pageUtils = new PageUtils(assistValveF8List, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("biz:mainValveF8:add")
	String add(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/mainValveF8/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:mainValveF8:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MainValveF8DO mainValveF8 = mainValveF8Service.get(id);
		model.addAttribute("mainValveF8", mainValveF8);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/mainValveF8/edit";
	}
	@GetMapping("/look/{id}")
	String look(@PathVariable("id") Long id,Model model){
		MainValveF8DO mainValveF8 = mainValveF8Service.get(id);
		model.addAttribute("mainValveF8", mainValveF8);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/mainValveF8/look";
	}

	//工作流开始页面
	@GetMapping("/form")
	String form(Model model) {
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/mainValveF8/add";
	}

	//工作流编辑页面
	@GetMapping("/form/{taskId}")
	String form(@PathVariable("taskId") String taskId, Model model) {
		Task task = activitiUtils.getTaskByTaskId(taskId);
		MainValveF8DO mainValveF8 = mainValveF8Service.get(Long.valueOf(activitiUtils.getBusinessKeyByTask(task)));
		mainValveF8.setTaskId(taskId);
		model.addAttribute("mainValveF8", mainValveF8);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/mainValveF8/edit";
	}

	//工作流流转（签字）
	@ResponseBody
	@RequestMapping("/sign")
	public R sign(MainValveF8DO mainValveF8){
		if(mainValveF8.getId() == null) {
			mainValveF8Service.save(mainValveF8);
		}
		if(StringUtils.isBlank(mainValveF8.getTaskId())) {
			HashMap map = new HashMap<>();
			map.put("processName","F8主阀");
			map.put("processNumber",mainValveF8.getPopValue());
			map.put("processForm","/biz/mainValveF8/form");
			Task task = actTaskService.startProcess(ActivitiConstant.ACTIVITI_PROCESS104[0],ActivitiConstant.ACTIVITI_PROCESS104[1],mainValveF8.getId().toString(),null,map);
			mainValveF8.setTaskId(task.getId());
			mainValveF8.setTaskName(task.getName());
			mainValveF8.setProcessInstanceId(task.getProcessInstanceId());
		}
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("pass",  mainValveF8.getTaskPass());
		Task task = actTaskService.complete(mainValveF8.getTaskId(),vars);
		if(task != null) {
			mainValveF8.setTaskId(task.getId());
			mainValveF8.setTaskName(task.getName());
		}
		mainValveF8Service.update(mainValveF8);
		return R.ok();
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:mainValveF8:add")
	public R save( MainValveF8DO mainValveF8){
		if(mainValveF8Service.save(mainValveF8)>0){
			String taskId = mainValveF8.getTaskId();
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
	@RequiresPermissions("biz:mainValveF8:edit")
	public R update( MainValveF8DO mainValveF8){
		String taskId = mainValveF8.getTaskId();
		if(StringUtils.isNotBlank(taskId)) {
			actTaskService.claim(taskId, getUsername());
		}
		mainValveF8Service.update(mainValveF8);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:mainValveF8:remove")
	public R remove( Long id){
		if(mainValveF8Service.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:mainValveF8:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		mainValveF8Service.batchRemove(ids);
		return R.ok();
	}
	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !mainValveF8Service.exit(params);
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
