package com.rbc.biz.controller;

import com.rbc.biz.domain.TaskInfoDO;
import com.rbc.biz.service.TaskInfoService;
import com.rbc.common.utils.PageUtils;
import com.rbc.common.utils.Query;
import com.rbc.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
public class TaskInfoController {
	@Autowired
	private TaskInfoService taskInfoService;
	
	@GetMapping()
	@RequiresPermissions("biz:taskInfo:taskInfo")
	String TaskInfo(){
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
	
	@GetMapping("/add")
	@RequiresPermissions("biz:taskInfo:add")
	String add(){
	    return "biz/taskInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:taskInfo:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TaskInfoDO taskInfo = taskInfoService.get(id);
		model.addAttribute("taskInfo", taskInfo);
	    return "biz/taskInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:taskInfo:add")
	public R save( TaskInfoDO taskInfo){
		if(taskInfoService.save(taskInfo)>0){
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
		taskInfoService.update(taskInfo);
		return R.ok();
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
	
}
