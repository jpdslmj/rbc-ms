package com.rbc.biz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rbc.activiti.config.ActivitiConstant;
import com.rbc.activiti.service.impl.ActTaskServiceImpl;
import com.rbc.activiti.utils.ActivitiUtils;
import com.rbc.biz.domain.*;
import com.rbc.biz.service.ToolInspectionService;
import com.rbc.biz.service.ToolOtherService;
import com.rbc.biz.service.WenchService;
import com.rbc.common.controller.BaseController;
import com.rbc.common.utils.*;
import com.rbc.system.domain.UserDO;
import com.rbc.system.service.UserService;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具检视信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:05
 */
 
@Controller
@RequestMapping("/biz/toolInspection")
public class ToolInspectionController extends BaseController {
	@Autowired
	private ToolInspectionService toolInspectionService;
	@Autowired
	WenchService wenchService;
	@Autowired
	ToolOtherService toolOtherService;
	@Autowired
	UserService userService;
	@Autowired
	private ActivitiUtils activitiUtils;
	@Autowired
	private ActTaskServiceImpl actTaskService;
	@GetMapping()
	@RequiresPermissions("biz:toolInspection:toolInspection")
	String ToolInspection(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/toolInspection/toolInspection";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:toolInspection:toolInspection")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ToolInspectionDO> toolInspectionList = toolInspectionService.list(query);
		int total = toolInspectionService.count(query);
		PageUtils pageUtils = new PageUtils(toolInspectionList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listNew")
	@RequiresPermissions("biz:toolInspection:toolInspection")
	public PageUtils listNew(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ToolInspectionDO> toolInspectionList = toolInspectionService.list(query);
		int total = toolInspectionService.count(query);
		PageUtils pageUtils = new PageUtils(toolInspectionList, total);
		return pageUtils;
	}
	@GetMapping("/add")
	@RequiresPermissions("biz:toolInspection:add")
	String add(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/toolInspection/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:toolInspection:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ToolInspectionDO toolInspection = toolInspectionService.get(id);
		model.addAttribute("toolInspection", toolInspection);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/toolInspection/edit";
	}

	/**
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/look/{id}")
	String look(@PathVariable("id") Long id,Model model){
		ToolInspectionDO toolInspection = toolInspectionService.get(id);
		model.addAttribute("toolInspection", toolInspection);
		UserDO userDO  = userService.get(getUserId());
		Map query = new HashMap();
		query.put("toolId",id);
		List<WenchDO> wenchList = wenchService.list(query);
		List<ToolOtherDO> otherToolList = toolOtherService.list(query);
		model.addAttribute("user",userDO);
		model.addAttribute("wenchList",wenchList);
		model.addAttribute("otherToolList",otherToolList);
		return "biz/toolInspection/look";
	}

	/**
	 * 打印
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/print/{id}")
	String print(@PathVariable("id") Long id,Model model){
		ToolInspectionDO toolInspection = toolInspectionService.get(id);
		model.addAttribute("toolInspection", toolInspection);
		UserDO userDO  = userService.get(getUserId());
		Map query = new HashMap();
		query.put("toolId",id);
		List<WenchDO> wenchList = wenchService.list(query);
		List<ToolOtherDO> otherToolList = toolOtherService.list(query);
		model.addAttribute("user",userDO);
		model.addAttribute("wenchList",wenchList);
		model.addAttribute("otherToolList",otherToolList);
		return "biz/toolInspection/toolInspectionPrint";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions(value = {"biz:toolInspection:add","biz:toolInspection:edit"},logical = Logical.OR)
	public R save( @RequestBody JSONObject jsonParam){
		System.out.println(jsonParam.toString());
		JSONObject toolInspection=jsonParam.getJSONObject("toolInspectionDo");
		JSONArray wench =jsonParam.getJSONArray("wenchDo");
		JSONArray toolOther =jsonParam.getJSONArray("toolOtherDo");

		ToolInspectionDO toolInspectionDo=JSON.parseObject(toolInspection.toJSONString(),ToolInspectionDO.class);

		Type typeWench = new TypeReference<List<WenchDO>>(){}.getType();
		List<WenchDO> wenchDoList = JSON.parseObject(wench.toJSONString(), typeWench);

		Type typeOtherTool = new TypeReference<List<ToolOtherDO>>(){}.getType();
		List<ToolOtherDO> toolOtherDoList = JSON.parseObject(toolOther.toJSONString(), typeOtherTool);
//		if(toolInspectionDo.getId()==null||toolInspectionDo.getId().equals("")){
//			toolInspectionDo.setCreateTime(new Date());
//			toolInspectionDo.setUpdateTime(new Date());
//		}else{
//			toolInspectionDo.setUpdateTime(new Date());
//		}
		int r=toolInspectionService.batchSaveOrUpdate(toolInspectionDo,wenchDoList,toolOtherDoList);

		if(r>0){
			String taskId = toolInspectionDo.getTaskId();
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
	@RequiresPermissions("biz:toolInspection:edit")
	public R update( ToolInspectionDO toolInspection){
		String taskId = toolInspection.getTaskId();
		if(StringUtils.isNotBlank(taskId)) {
			actTaskService.claim(taskId, getUsername());
		}
		toolInspectionService.update(toolInspection);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:toolInspection:remove")
	public R remove( Long id){
		if(toolInspectionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:toolInspection:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		toolInspectionService.batchRemove(ids);
		return R.ok();
	}
	@ResponseBody
	@GetMapping("/listWench")
	@RequiresPermissions("biz:toolInspection:toolInspection")
	public PageUtils listWench(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<WenchDO> wenchList = wenchService.list(query);
		int total = wenchService.count(query);
		PageUtils pageUtils = new PageUtils(wenchList, total);
		return pageUtils;
	}


	@ResponseBody
	@GetMapping("/listOtherTool")
	@RequiresPermissions("biz:toolInspection:toolInspection")
	public PageUtils listOtherTool(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ToolOtherDO> otherToolList = toolOtherService.list(query);
		int total = toolOtherService.count(query);
		PageUtils pageUtils = new PageUtils(otherToolList, total);
		return pageUtils;
	}

	@ResponseBody
	@PostMapping("/batchRemoveOtherTool")
	@RequiresPermissions("biz:toolInspection:toolInspection")
	public R batchRemoveOtherTool(@RequestParam("ids[]") Long[] ids){
		toolOtherService.batchRemove(ids);
		return R.ok();
	}
	@ResponseBody
	@PostMapping("/batchRemoveWench")
	@RequiresPermissions("biz:toolInspection:toolInspection")
	public R batchRemoveWench(@RequestParam("ids[]") Long[] ids){
		wenchService.batchRemove(ids);
		return R.ok();
	}

	//工作流开始页面
	@GetMapping("/form")
	String form(Model model) {
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/toolInspection/add";
	}

	//工作流编辑页面
	@GetMapping("/form/{taskId}")
	String form(@PathVariable("taskId") String taskId, Model model) {
		Task task = activitiUtils.getTaskByTaskId(taskId);
		ToolInspectionDO toolInspection = toolInspectionService.get(Long.valueOf(activitiUtils.getBusinessKeyByTask(task)));
		toolInspection.setTaskId(taskId);
		model.addAttribute("toolInspection", toolInspection);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/toolInspection/edit";
	}

	//工作流流转（签字）
	@ResponseBody
	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public R sign(@RequestBody JSONObject jsonParam){
		JSONObject toolInspection=jsonParam.getJSONObject("toolInspectionDo");
		JSONArray wench =jsonParam.getJSONArray("wenchDo");
		JSONArray toolOther =jsonParam.getJSONArray("toolOtherDo");

		ToolInspectionDO toolInspectionDo=JSON.parseObject(toolInspection.toJSONString(),ToolInspectionDO.class);

		Type typeWench = new TypeReference<List<WenchDO>>(){}.getType();
		List<WenchDO> wenchDoList = JSON.parseObject(wench.toJSONString(), typeWench);

		Type typeOtherTool = new TypeReference<List<ToolOtherDO>>(){}.getType();
		List<ToolOtherDO> toolOtherDoList = JSON.parseObject(toolOther.toJSONString(), typeOtherTool);
//		if(toolInspectionDo.getId()==null||toolInspectionDo.getId().equals("")){
//			toolInspectionDo.setCreateTime(new Date());
//			toolInspectionDo.setUpdateTime(new Date());
//		}else{
//			toolInspectionDo.setUpdateTime(new Date());
//		}
		int r=toolInspectionService.batchSaveOrUpdate(toolInspectionDo,wenchDoList,toolOtherDoList);

		if(r>0){
			if(StringUtils.isBlank(toolInspectionDo.getTaskId())) {
				HashMap map = new HashMap<>();
				map.put("processName","检修设备检视");
				Map params = new HashMap();
				params.put("fixWorkerName", toolInspectionDo.getFixWorkerName());
				params.put("createTime", toolInspectionDo.getCreateTime());
				map.put("params", params);
				map.put("processForm","/biz/toolInspection/form");
				map.put("fixWorkerNo",toolInspectionDo.getFixWorkerNo());
				map.put("createTime",DateUtils.format(toolInspectionDo.getCreateTime()));
				Task task = actTaskService.startProcess(ActivitiConstant.ACTIVITI_INSPECTION[0],ActivitiConstant.ACTIVITI_INSPECTION[1],toolInspectionDo.getId().toString(),null,map);
				toolInspectionDo.setTaskId(task.getId());
				toolInspectionDo.setTaskName(task.getName());
				toolInspectionDo.setProcessInstanceId(task.getProcessInstanceId());
			}
			Map<String,Object> vars = new HashMap<>(16);
			vars.put("pass",  toolInspectionDo.getTaskPass());
			vars.put("fixWorkerNo",toolInspectionDo.getFixWorkerNo());
			Task task = actTaskService.complete(toolInspectionDo.getTaskId(),vars);
			if(task != null) {
				toolInspectionDo.setTaskId(task.getId());
				toolInspectionDo.setTaskName(task.getName());
			}
			toolInspectionService.update(toolInspectionDo);
		}
		return R.ok();
	}
}
