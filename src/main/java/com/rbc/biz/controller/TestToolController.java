package com.rbc.biz.controller;

import com.rbc.activiti.config.ActivitiConstant;
import com.rbc.activiti.service.impl.ActTaskServiceImpl;
import com.rbc.activiti.utils.ActivitiUtils;
import com.rbc.biz.domain.OptionsDO;
import com.rbc.biz.domain.TestToolDO;
import com.rbc.biz.service.TestToolService;
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
 * 试验工具检视信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
 
@Controller
@RequestMapping("/biz/testTool")
public class TestToolController extends BaseController {
	@Autowired
	private TestToolService testToolService;
	@Autowired
	UserService userService;
	@Autowired
	private ActivitiUtils activitiUtils;
	@Autowired
	private ActTaskServiceImpl actTaskService;
	@GetMapping()
	@RequiresPermissions("biz:testTool:testTool")
	String TestTool(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/testTool/testTool";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:testTool:testTool")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TestToolDO> testToolList = testToolService.list(query);
		int total = testToolService.count(query);
		PageUtils pageUtils = new PageUtils(testToolList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listNew")
	@RequiresPermissions("biz:testTool:testTool")
	public PageUtils listNew(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<TestToolDO> testToolList = testToolService.list(query);
		int total = testToolService.count(query);
		PageUtils pageUtils = new PageUtils(testToolList, total);
		return pageUtils;
	}
	@GetMapping("/add")
	@RequiresPermissions("biz:testTool:add")
	String add(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/testTool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:testTool:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TestToolDO testTool = testToolService.get(id);
		model.addAttribute("testTool", testTool);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/testTool/edit";
	}
	@GetMapping("/look/{id}")
	String look(@PathVariable("id") Long id,Model model){
		TestToolDO testTool = testToolService.get(id);
		model.addAttribute("testTool", testTool);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/testTool/look";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:testTool:add")
	public R save( TestToolDO testTool){
		testTool.setCreateTime(new Date());
		testTool.setUpdateTime(new Date());
		if(testToolService.save(testTool)>0){
			String taskId = testTool.getTaskId();
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
	@RequiresPermissions("biz:testTool:edit")
	public R update( TestToolDO testTool){
		String taskId = testTool.getTaskId();
		if(StringUtils.isNotBlank(taskId)) {
			actTaskService.claim(taskId, getUsername());
		}
		testTool.setUpdateTime(new Date());
		testToolService.update(testTool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:testTool:remove")
	public R remove( Long id){
		if(testToolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:testTool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		testToolService.batchRemove(ids);
		return R.ok();
	}
	//工作流开始页面
	@GetMapping("/form")
	String form(Model model) {
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/testTool/add";
	}

	//工作流编辑页面
	@GetMapping("/form/{taskId}")
	String form(@PathVariable("taskId") String taskId, Model model) {
		Task task = activitiUtils.getTaskByTaskId(taskId);
		TestToolDO testTool = testToolService.get(Long.valueOf(activitiUtils.getBusinessKeyByTask(task)));
		testTool.setTaskId(taskId);
		model.addAttribute("testTool", testTool);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/testTool/edit";
	}

	//工作流流转（签字）
	@ResponseBody
	@RequestMapping("/sign")
	public R sign(TestToolDO testTool){
		if(testTool.getId() == null) {
			testTool.setCreateTime(new Date());
			testTool.setUpdateTime(new Date());
			testToolService.save(testTool);
		}
		if(StringUtils.isBlank(testTool.getTaskId())) {
			HashMap map = new HashMap<>();
			map.put("processName","试验设备检视");
			Map params = new HashMap();
			params.put("testerNo", testTool.getTesterNo());
			params.put("testerName", testTool.getTesterName());
			map.put("params", params);
			map.put("processForm","/biz/testTool/form");
			//map.put("testerNo", testTool.getTesterNo());
			map.put("fixWorkerNo", testTool.getTesterNo());
			map.put("createTime",DateUtils.format(testTool.getCreateTime()));
			Task task = actTaskService.startProcess(ActivitiConstant.ACTIVITI_INSPECTION[0],ActivitiConstant.ACTIVITI_INSPECTION[1],testTool.getId().toString(),null,map);
			testTool.setTaskId(task.getId());
			testTool.setTaskName(task.getName());
			testTool.setProcessInstanceId(task.getProcessInstanceId());
		}
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("pass",  testTool.getTaskPass());
		Task task = actTaskService.complete(testTool.getTaskId(),vars);
		if(task != null) {
			testTool.setTaskId(task.getId());
			testTool.setTaskName(task.getName());
		}
		testToolService.update(testTool);
		return R.ok();
	}
}
