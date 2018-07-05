package com.rbc.biz.controller;

import com.rbc.biz.domain.TestPartsDO;
import com.rbc.biz.service.TestPartsService;
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
 * 试验部件信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
 
@Controller
@RequestMapping("/biz/testParts")
public class TestPartsController {
	@Autowired
	private TestPartsService testPartsService;
	
	@GetMapping()
	@RequiresPermissions("biz:testParts:testParts")
	String TestParts(){
	    return "biz/testParts/testParts";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:testParts:testParts")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TestPartsDO> testPartsList = testPartsService.list(query);
		int total = testPartsService.count(query);
		PageUtils pageUtils = new PageUtils(testPartsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:testParts:add")
	String add(){
	    return "biz/testParts/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:testParts:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TestPartsDO testParts = testPartsService.get(id);
		model.addAttribute("testParts", testParts);
	    return "biz/testParts/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:testParts:add")
	public R save( TestPartsDO testParts){
		if(testPartsService.save(testParts)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:testParts:edit")
	public R update( TestPartsDO testParts){
		testPartsService.update(testParts);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:testParts:remove")
	public R remove( Long id){
		if(testPartsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:testParts:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		testPartsService.batchRemove(ids);
		return R.ok();
	}
	
}
