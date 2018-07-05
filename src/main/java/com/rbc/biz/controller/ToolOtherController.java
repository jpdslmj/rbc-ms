package com.rbc.biz.controller;

import com.rbc.biz.domain.ToolOtherDO;
import com.rbc.biz.service.ToolOtherService;
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
 * 其他问题工具信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:05
 */
 
@Controller
@RequestMapping("/biz/toolOther")
public class ToolOtherController {
	@Autowired
	private ToolOtherService toolOtherService;
	
	@GetMapping()
	@RequiresPermissions("biz:toolOther:toolOther")
	String ToolOther(){
	    return "biz/toolOther/toolOther";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:toolOther:toolOther")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ToolOtherDO> toolOtherList = toolOtherService.list(query);
		int total = toolOtherService.count(query);
		PageUtils pageUtils = new PageUtils(toolOtherList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:toolOther:add")
	String add(){
	    return "biz/toolOther/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:toolOther:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ToolOtherDO toolOther = toolOtherService.get(id);
		model.addAttribute("toolOther", toolOther);
	    return "biz/toolOther/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:toolOther:add")
	public R save( ToolOtherDO toolOther){
		if(toolOtherService.save(toolOther)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:toolOther:edit")
	public R update( ToolOtherDO toolOther){
		toolOtherService.update(toolOther);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:toolOther:remove")
	public R remove( Long id){
		if(toolOtherService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:toolOther:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		toolOtherService.batchRemove(ids);
		return R.ok();
	}
	
}
