package com.rbc.biz.controller;

import com.rbc.biz.domain.TaskDistributionDO;
import com.rbc.biz.service.TaskDistributionService;
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
 * 任务发布表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
 
@Controller
@RequestMapping("/biz/taskDistribution")
public class TaskDistributionController {
	@Autowired
	private TaskDistributionService taskDistributionService;
	
	@GetMapping()
	@RequiresPermissions("biz:taskDistribution:taskDistribution")
	String TaskDistribution(){
	    return "biz/taskDistribution/taskDistribution";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:taskDistribution:taskDistribution")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TaskDistributionDO> taskDistributionList = taskDistributionService.list(query);
		int total = taskDistributionService.count(query);
		PageUtils pageUtils = new PageUtils(taskDistributionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:taskDistribution:add")
	String add(){
	    return "biz/taskDistribution/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:taskDistribution:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TaskDistributionDO taskDistribution = taskDistributionService.get(id);
		model.addAttribute("taskDistribution", taskDistribution);
	    return "biz/taskDistribution/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:taskDistribution:add")
	public R save( TaskDistributionDO taskDistribution){
		if(taskDistributionService.save(taskDistribution)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:taskDistribution:edit")
	public R update( TaskDistributionDO taskDistribution){
		taskDistributionService.update(taskDistribution);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:taskDistribution:remove")
	public R remove( Long id){
		if(taskDistributionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:taskDistribution:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		taskDistributionService.batchRemove(ids);
		return R.ok();
	}
	
}
