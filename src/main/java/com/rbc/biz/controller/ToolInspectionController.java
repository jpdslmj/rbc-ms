package com.rbc.biz.controller;

import com.rbc.biz.domain.ToolInspectionDO;
import com.rbc.biz.service.ToolInspectionService;
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
 * 工具检视信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:05
 */
 
@Controller
@RequestMapping("/biz/toolInspection")
public class ToolInspectionController {
	@Autowired
	private ToolInspectionService toolInspectionService;
	
	@GetMapping()
	@RequiresPermissions("biz:toolInspection:toolInspection")
	String ToolInspection(){
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
	
	@GetMapping("/add")
	@RequiresPermissions("biz:toolInspection:add")
	String add(){
	    return "biz/toolInspection/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:toolInspection:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ToolInspectionDO toolInspection = toolInspectionService.get(id);
		model.addAttribute("toolInspection", toolInspection);
	    return "biz/toolInspection/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:toolInspection:add")
	public R save( ToolInspectionDO toolInspection){
		if(toolInspectionService.save(toolInspection)>0){
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
	
}
