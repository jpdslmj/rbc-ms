package com.rbc.biz.controller;

import com.rbc.biz.domain.WenchDO;
import com.rbc.biz.service.WenchService;
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
 * 问题扳手信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:05
 */
 
@Controller
@RequestMapping("/biz/wench")
public class WenchController {
	@Autowired
	private WenchService wenchService;
	
	@GetMapping()
	@RequiresPermissions("biz:wench:wench")
	String Wench(){
	    return "biz/wench/wench";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:wench:wench")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WenchDO> wenchList = wenchService.list(query);
		int total = wenchService.count(query);
		PageUtils pageUtils = new PageUtils(wenchList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:wench:add")
	String add(){
	    return "biz/wench/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:wench:edit")
	String edit(@PathVariable("id") Long id,Model model){
		WenchDO wench = wenchService.get(id);
		model.addAttribute("wench", wench);
	    return "biz/wench/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:wench:add")
	public R save( WenchDO wench){
		if(wenchService.save(wench)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:wench:edit")
	public R update( WenchDO wench){
		wenchService.update(wench);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:wench:remove")
	public R remove( Long id){
		if(wenchService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:wench:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		wenchService.batchRemove(ids);
		return R.ok();
	}
	
}
