package com.rbc.biz.controller;

import com.rbc.biz.domain.AssistValveF8DO;
import com.rbc.biz.service.AssistValveF8Service;
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
 * F8辅助阀信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
 
@Controller
@RequestMapping("/biz/assistValveF8")
public class AssistValveF8Controller {
	@Autowired
	private AssistValveF8Service assistValveF8Service;
	
	@GetMapping()
	@RequiresPermissions("biz:assistValveF8:assistValveF8")
	String AssistValveF8(){
	    return "biz/assistValveF8/assistValveF8";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:assistValveF8:assistValveF8")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AssistValveF8DO> assistValveF8List = assistValveF8Service.list(query);
		int total = assistValveF8Service.count(query);
		PageUtils pageUtils = new PageUtils(assistValveF8List, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:assistValveF8:add")
	String add(){
	    return "biz/assistValveF8/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:assistValveF8:edit")
	String edit(@PathVariable("id") Long id,Model model){
		AssistValveF8DO assistValveF8 = assistValveF8Service.get(id);
		model.addAttribute("assistValveF8", assistValveF8);
	    return "biz/assistValveF8/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:assistValveF8:add")
	public R save( AssistValveF8DO assistValveF8){
		if(assistValveF8Service.save(assistValveF8)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:assistValveF8:edit")
	public R update( AssistValveF8DO assistValveF8){
		assistValveF8Service.update(assistValveF8);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:assistValveF8:remove")
	public R remove( Long id){
		if(assistValveF8Service.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:assistValveF8:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		assistValveF8Service.batchRemove(ids);
		return R.ok();
	}
	
}
