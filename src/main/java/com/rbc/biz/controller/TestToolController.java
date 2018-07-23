package com.rbc.biz.controller;

import com.rbc.biz.domain.TestToolDO;
import com.rbc.biz.service.TestToolService;
import com.rbc.common.controller.BaseController;
import com.rbc.common.utils.PageUtils;
import com.rbc.common.utils.Query;
import com.rbc.common.utils.R;
import com.rbc.system.domain.UserDO;
import com.rbc.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 试验工具检视信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
 
@Controller
@RequestMapping("/biz/testTool")
public class TestToolController extends BaseController {
	@Autowired
	private TestToolService testToolService;
	@Autowired
	UserService userService;
	@GetMapping()
	@RequiresPermissions("biz:testTool:testTool")
	String TestTool(){
	    return "biz/testTool/testTool";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:testTool:testTool")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TestToolDO> testToolList = testToolService.list(query);
		int total = testToolService.count(query);
		PageUtils pageUtils = new PageUtils(testToolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:testTool:add")
	String add(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/testTool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:testTool:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TestToolDO testTool = testToolService.get(id);
		model.addAttribute("testTool", testTool);
	    return "biz/testTool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:testTool:add")
	public R save( TestToolDO testTool){
		testTool.setCreateTime(new Date());
		testTool.setUpdateTime(new Date());
		if(testToolService.save(testTool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:testTool:edit")
	public R update( TestToolDO testTool){
		testTool.setUpdateTime(new Date());
		testToolService.update(testTool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:testTool:remove")
	public R remove( Long id){
		if(testToolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:testTool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		testToolService.batchRemove(ids);
		return R.ok();
	}
	
}
