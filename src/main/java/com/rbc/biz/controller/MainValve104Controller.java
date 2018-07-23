package com.rbc.biz.controller;

import java.util.List;
import java.util.Map;

import com.rbc.common.controller.BaseController;
import com.rbc.system.domain.UserDO;
import com.rbc.system.service.RoleService;
import com.rbc.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rbc.biz.domain.MainValve104DO;
import com.rbc.biz.service.MainValve104Service;
import com.rbc.common.utils.PageUtils;
import com.rbc.common.utils.Query;
import com.rbc.common.utils.R;

/**
 * 104主阀信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-23 00:13:25
 */
 
@Controller
@RequestMapping("/biz/mainValve104")
public class MainValve104Controller extends BaseController {
	@Autowired
	private MainValve104Service mainValve104Service;
	@Autowired
	private UserService userService;
	
	@GetMapping()
	@RequiresPermissions("biz:mainValve104:mainValve104")
	String MainValve104(){
	    return "biz/mainValve104/mainValve104";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:mainValve104:mainValve104")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MainValve104DO> mainValve104List = mainValve104Service.list(query);
		int total = mainValve104Service.count(query);
		PageUtils pageUtils = new PageUtils(mainValve104List, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:mainValve104:add")
	String add(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/mainValve104/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:mainValve104:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MainValve104DO mainValve104 = mainValve104Service.get(id);
		model.addAttribute("mainValve104", mainValve104);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/mainValve104/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:mainValve104:add")
	public R save( MainValve104DO mainValve104){
		if(mainValve104Service.save(mainValve104)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:mainValve104:edit")
	public R update( MainValve104DO mainValve104){
		mainValve104Service.update(mainValve104);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:mainValve104:remove")
	public R remove( Long id){
		if(mainValve104Service.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:mainValve104:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		mainValve104Service.batchRemove(ids);
		return R.ok();
	}
	
}
