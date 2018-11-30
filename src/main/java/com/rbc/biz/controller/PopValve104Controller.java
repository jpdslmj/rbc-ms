package com.rbc.biz.controller;

import com.rbc.activiti.config.ActivitiConstant;
import com.rbc.activiti.domain.SalaryDO;
import com.rbc.activiti.service.impl.ActTaskServiceImpl;
import com.rbc.activiti.utils.ActivitiUtils;
import com.rbc.activiti.vo.TaskVO;
import com.rbc.biz.domain.MainValve104DO;
import com.rbc.biz.domain.PopValve104DO;
import com.rbc.biz.domain.ToolInspectionDO;
import com.rbc.biz.service.PopValve104Service;
import com.rbc.biz.service.ToolInspectionService;
import com.rbc.common.controller.BaseController;
import com.rbc.common.utils.*;
import com.rbc.system.domain.UserDO;
import com.rbc.system.service.RoleService;
import com.rbc.system.service.UserService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 104紧急阀信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
 
@Controller
@RequestMapping("/biz/popValve104")
public class PopValve104Controller extends BaseController {
	@Autowired
	private PopValve104Service popValve104Service;
    @Autowired
    private UserService userService;
	@Autowired
    private ActivitiUtils activitiUtils;
	@Autowired
	private ActTaskServiceImpl actTaskService;
	@GetMapping()
	@RequiresPermissions("biz:popValve104:popValve104")
	String PopValve104(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/popValve104/popValve104";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:popValve104:popValve104")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<PopValve104DO> popValve104List = popValve104Service.list(query);
		int total = popValve104Service.count(query);
		PageUtils pageUtils = new PageUtils(popValve104List, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listNew")
	@RequiresPermissions("biz:popValve104:popValve104")
	public PageUtils listNew(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<PopValve104DO> popValve104List = popValve104Service.list(query);
		int total = popValve104Service.count(query);
		PageUtils pageUtils = new PageUtils(popValve104List, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/history/list")
	@RequiresPermissions("biz:popValve104:popValve104")
	public PageUtils historyList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<PopValve104DO> popValve104List = popValve104Service.list(query);
		int total = popValve104Service.count(query);
		PageUtils pageUtils = new PageUtils(popValve104List, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:popValve104:add")
    String add(Model model){
        UserDO userDO  = userService.get(getUserId());
        model.addAttribute("user",userDO);
        return "biz/popValve104/add";
    }

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:popValve104:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PopValve104DO popValve104 = popValve104Service.get(id);
		model.addAttribute("popValve104", popValve104);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/popValve104/edit";
	}

    @GetMapping("/look/{id}")
    String look(@PathVariable("id") Long id,Model model){
        PopValve104DO popValve104 = popValve104Service.get(id);
        model.addAttribute("popValve104", popValve104);
        UserDO userDO  = userService.get(getUserId());
        model.addAttribute("user",userDO);
        return "biz/popValve104/look";
    }

	//工作流开始页面
	@GetMapping("/form")
	String form(Model model) {
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/popValve104/add";
	}

	//工作流编辑页面
	@GetMapping("/form/{taskId}")
	String form(@PathVariable("taskId") String taskId, Model model) {
		Task task = activitiUtils.getTaskByTaskId(taskId);
		PopValve104DO popValve104 = popValve104Service.get(Long.valueOf(activitiUtils.getBusinessKeyByTask(task)));
		popValve104.setTaskId(taskId);
		model.addAttribute("popValve104", popValve104);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/popValve104/edit";
	}

	//工作流流转（签字）
	@ResponseBody
	@RequestMapping("/sign")
	public R sign(PopValve104DO popValve104){
		if(popValve104.getId() == null) {
			Date crtTm= new Date();
			popValve104.setCreateTime(crtTm);
			popValve104Service.save(popValve104);
		}
		if(StringUtils.isBlank(popValve104.getTaskId())) {
			HashMap map = new HashMap<>();
			map.put("processName","104紧急阀");
			map.put("processNumber",popValve104.getPopValue());
			map.put("processForm","/biz/popValve104/form");
			map.put("createTime",DateUtils.format(popValve104.getCreateTime()));
			Task task = actTaskService.startProcess(ActivitiConstant.ACTIVITI_PROCESS104[0],ActivitiConstant.ACTIVITI_PROCESS104[1],popValve104.getId().toString(),null,map);
			popValve104.setTaskId(task.getId());
			popValve104.setTaskName(task.getName());
			popValve104.setProcessInstanceId(task.getProcessInstanceId());
		}
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("pass",  popValve104.getTaskPass());
		Task task = actTaskService.complete(popValve104.getTaskId(),vars);
		if(task != null) {
			popValve104.setTaskId(task.getId());
			popValve104.setTaskName(task.getName());
		}
		popValve104Service.update(popValve104);
		return R.ok();
	}

	@GetMapping("/history/{id}")
	//@RequiresPermissions("biz:popValve104:edit")
	String historydetail(@PathVariable("id") Long id,Model model){
		PopValve104DO popValve104 = popValve104Service.get(id);
		model.addAttribute("popValve104", popValve104);
		return "biz/popValve104/history_detail";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:popValve104:add")
	public R save( PopValve104DO popValve104){
		if(popValve104.getId() == null) {
			Date crtTm= new Date();
			popValve104.setCreateTime(crtTm);
		}
		if(popValve104Service.save(popValve104)>0){
		    String taskId = popValve104.getTaskId();
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
	@RequiresPermissions("biz:popValve104:edit")
	public R update( PopValve104DO popValve104){
		String taskId = popValve104.getTaskId();
		if(StringUtils.isNotBlank(taskId)) {
			actTaskService.claim(taskId, getUsername());
		}
		popValve104Service.update(popValve104);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:popValve104:remove")
	public R remove( Long id){
		if(popValve104Service.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:popValve104:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		popValve104Service.batchRemove(ids);
		return R.ok();
	}
	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !popValve104Service.exit(params);
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
		params.put("username",userDO.getUsername());
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
