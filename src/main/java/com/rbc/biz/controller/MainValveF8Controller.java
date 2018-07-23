package com.rbc.biz.controller;

import java.util.List;
import java.util.Map;

import com.rbc.common.controller.BaseController;
import com.rbc.system.domain.UserDO;
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

import com.rbc.biz.domain.MainValveF8DO;
import com.rbc.biz.service.MainValveF8Service;
import com.rbc.common.utils.PageUtils;
import com.rbc.common.utils.Query;
import com.rbc.common.utils.R;

/**
 * F8主阀信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-23 00:13:25
 */
 
@Controller
@RequestMapping("/biz/mainValveF8")
public class MainValveF8Controller extends BaseController {
	@Autowired
	private MainValveF8Service mainValveF8Service;
	@Autowired
	private UserService userService;
	@GetMapping()
	@RequiresPermissions("biz:mainValveF8:mainValveF8")
	String MainValveF8(){
	    return "biz/mainValveF8/mainValveF8";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:mainValveF8:mainValveF8")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MainValveF8DO> mainValveF8List = mainValveF8Service.list(query);
		int total = mainValveF8Service.count(query);
		PageUtils pageUtils = new PageUtils(mainValveF8List, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:mainValveF8:add")
	String add(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/mainValveF8/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:mainValveF8:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MainValveF8DO mainValveF8 = mainValveF8Service.get(id);
		model.addAttribute("mainValveF8", mainValveF8);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/mainValveF8/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:mainValveF8:add")
	public R save( MainValveF8DO mainValveF8){
		if(mainValveF8Service.save(mainValveF8)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:mainValveF8:edit")
	public R update( MainValveF8DO mainValveF8){
		mainValveF8Service.update(mainValveF8);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:mainValveF8:remove")
	public R remove( Long id){
		if(mainValveF8Service.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:mainValveF8:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		mainValveF8Service.batchRemove(ids);
		return R.ok();
	}
	
}
