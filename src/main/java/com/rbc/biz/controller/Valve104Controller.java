package com.rbc.biz.controller;

import com.rbc.biz.domain.MainValve104DO;
import com.rbc.biz.service.MainValve104Service;
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

 
@Controller
@RequestMapping("")
public class Valve104Controller {
	@Autowired
	private MainValve104Service mainValve104Service;
	
	@GetMapping("/part/m104chk")
	@RequiresPermissions("part:m104chk:m104chk")
	String m104chk(){
	    return "part/m104chk/m104chk";
	}
	
	@ResponseBody
	@GetMapping("/part/m104chk/list")
	@RequiresPermissions("part:m104chk:m104chk")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MainValve104DO> mainValve104DOList = mainValve104Service.list(query);
		int total = mainValve104Service.count(query);
		PageUtils pageUtils = new PageUtils(mainValve104DOList, total);
		return pageUtils;
	}
	
	@GetMapping("/part/m104chk/add")
	@RequiresPermissions("part:m104chk:add")
	String add(){
	    return "part/m104chk/add";
	}

	@GetMapping("/part/m104chk/edit/{id}")
	@RequiresPermissions("part:m104chk:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MainValve104DO mainValve104DO = mainValve104Service.get(id);
		model.addAttribute("mainValve104", mainValve104DO);
	    return "part/m104chk/editTask";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/part/m104chk/save")
	@RequiresPermissions("part:m104chk:add")
	public R save(MainValve104DO mainValve104DO){
		if(mainValve104Service.save(mainValve104DO)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/part/m104chk/update")
	@RequiresPermissions("part:m104chk:edit")
	public R update(MainValve104DO mainvalve){
		mainValve104Service.update(mainvalve);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/part/m104chk/remove")
	@ResponseBody
	@RequiresPermissions("part:m104chk:remove")
	public R remove( Long id){
		if(mainValve104Service.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/part/m104chk/batchRemove")
	@ResponseBody
	@RequiresPermissions("part:m104chk:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		mainValve104Service.batchRemove(ids);
		return R.ok();
	}
	
}
