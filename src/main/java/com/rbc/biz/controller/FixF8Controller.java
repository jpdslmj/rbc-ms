package com.rbc.biz.controller;

import com.rbc.biz.domain.FixEquipmentDO;
import com.rbc.biz.service.FixEquipmentService;
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
public class Fix104Controller {
	@Autowired
	private FixEquipmentService fixEquipmentService;

	/**
	 * 104主阀部件检修审核列表页
	 */
	@GetMapping("/part/fix104m")
	@RequiresPermissions("part:fix104m:fix104m")
	String fix104m(){
		return "part/fix104m/fix104m";
	}
	/**
	 * 104主阀部件检修审核列表页
	 */
	@GetMapping("/part/m104chk")
	@RequiresPermissions("part:m104chk:m104chk")
	String m104chk(){
		return "part/m104chk/m104chk";
	}



	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:fixEquipment:fixEquipment")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FixEquipmentDO> fixEquipmentList = fixEquipmentService.list(query);
		int total = fixEquipmentService.count(query);
		PageUtils pageUtils = new PageUtils(fixEquipmentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:fixEquipment:add")
	String add(){
	    return "biz/fixEquipment/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:fixEquipment:edit")
	String edit(@PathVariable("id") Long id,Model model){
		FixEquipmentDO fixEquipment = fixEquipmentService.get(id);
		model.addAttribute("fixEquipment", fixEquipment);
	    return "biz/fixEquipment/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:fixEquipment:add")
	public R save( FixEquipmentDO fixEquipment){
		if(fixEquipmentService.save(fixEquipment)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:fixEquipment:edit")
	public R update( FixEquipmentDO fixEquipment){
		fixEquipmentService.update(fixEquipment);
		return R.ok();
	}

	
}
