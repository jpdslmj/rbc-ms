package com.rbc.biz.controller;

import java.util.List;
import java.util.Map;

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

import com.rbc.biz.domain.OptionsDO;
import com.rbc.biz.service.OptionsService;
import com.rbc.common.utils.PageUtils;
import com.rbc.common.utils.Query;
import com.rbc.common.utils.R;

/**
 * 任务项目表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-21 10:57:36
 */
 
@Controller
@RequestMapping("/biz/options")
public class OptionsController {
	@Autowired
	private OptionsService optionsService;
	
	@GetMapping()
	@RequiresPermissions("biz:options:options")
	String Options(){
	    return "biz/options/options";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:options:options")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OptionsDO> optionsList = optionsService.list(query);
		int total = optionsService.count(query);
		PageUtils pageUtils = new PageUtils(optionsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:options:add")
	String add(){
	    return "biz/options/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:options:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OptionsDO options = optionsService.get(id);
		model.addAttribute("options", options);
	    return "biz/options/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:options:add")
	public R save( OptionsDO options){
		if(optionsService.save(options)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:options:edit")
	public R update( OptionsDO options){
		optionsService.update(options);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:options:remove")
	public R remove( Integer id){
		if(optionsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:options:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		optionsService.batchRemove(ids);
		return R.ok();
	}
	
}
