package com.rbc.biz.controller;

import com.rbc.biz.domain.MainValve104DO;
import com.rbc.biz.service.MainValve104Service;
import com.rbc.common.controller.BaseController;
import com.rbc.common.utils.PageUtils;
import com.rbc.common.utils.Query;
import com.rbc.common.utils.R;
import com.rbc.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

 
@Controller
@RequestMapping("")
public class MainValve104Controller extends BaseController {
	@Autowired
	private MainValve104Service mainValve104Service;

	/**
	 * 104主阀部件检修列表页
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


	/**
	 * 104主阀部件检修审核列表
	 */
	@ResponseBody
	@GetMapping("/part/m104chk/list")
	@RequiresPermissions("part:m104chk:m104chk")
	public PageUtils listM104chk(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<MainValve104DO> mainValve104DOList = mainValve104Service.list(query);
		int total = mainValve104Service.count(query);
		PageUtils pageUtils = new PageUtils(mainValve104DOList, total);
		return pageUtils;
	}

	@GetMapping("/part/m104chk/add")
	@RequiresPermissions("part:m104chk:add")
	String addM104chk(){
		return "part/m104chk/add";
	}

	/**
	 * 104主阀部件检修审核跳转
	 */
	@GetMapping("/part/m104chk/edit/{id}")
	@RequiresPermissions("part:m104chk:edit")
	String editM104chk(@PathVariable("id") Long id,Model model){
		MainValve104DO mainValve104DO = mainValve104Service.get(id);
		model.addAttribute("mainValve104", mainValve104DO);
		return "part/m104chk/editTask";
	}


	/**
	 * 104主阀部件检修审核保存
	 */
	@ResponseBody
	@PostMapping("/part/m104chk/save")
	@RequiresPermissions("part:m104chk:add")
	public R saveM104chk(MainValve104DO mainValve104DO){
		if(mainValve104Service.save(mainValve104DO)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 104主阀部件检修审核更新
	 */
	@ResponseBody
	@RequestMapping("/part/m104chk/update")
	@RequiresPermissions("part:m104chk:edit")
	public R updateM104chk(MainValve104DO mainvalve){
		mainValve104Service.update(mainvalve);
		return R.ok();
	}



	/**
	 * 104主阀部件检修列表
	 */
	@ResponseBody
	@GetMapping("/part/fix104m/list")
	@RequiresPermissions("part:fix104m:fix104m")
	public PageUtils listFix104m(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<MainValve104DO> mainValve104DOList = mainValve104Service.list(query);
		int total = mainValve104Service.count(query);
		PageUtils pageUtils = new PageUtils(mainValve104DOList, total);
		return pageUtils;
	}
	@GetMapping("/part/fix104m/add")
	@RequiresPermissions("part:fix104m:add")
	String addFix104mTask(Model model){
		UserDO userDO = getUser();
		model.addAttribute("userDO",userDO);
		return "part/fix104m/addTask";
	}
	/**
	 * 104主阀部件检修任务添加
	 */
	@ResponseBody
	@PostMapping("/part/fix104m/saveFixTask")
	@RequiresPermissions("part:fix104m:add")
	public R saveFix104m(MainValve104DO mainValve104DO){
		if(mainValve104Service.save(mainValve104DO)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 104主阀部件检修更新跳转
	 */
	@GetMapping("/part/fix104m/edit/{id}")
	@RequiresPermissions("part:fix104m:edit")
	String editFix104m(@PathVariable("id") Long id,Model model){
		MainValve104DO mainValve104DO = mainValve104Service.get(id);
		model.addAttribute("mainValve104", mainValve104DO);
		return "part/fix104m/editTask";
	}

	/**
	 * 104主阀部件检修更新
	 */
	@ResponseBody
	@RequestMapping("/part/fix104m/update")
	@RequiresPermissions("part:fix104m:edit")
	public R updateFix104m(MainValve104DO mainvalve){
		mainValve104Service.update(mainvalve);
		return R.ok();
	}



}
