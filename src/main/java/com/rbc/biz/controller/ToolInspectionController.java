package com.rbc.biz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rbc.biz.domain.*;
import com.rbc.biz.service.ToolInspectionService;
import com.rbc.biz.service.ToolOtherService;
import com.rbc.biz.service.WenchService;
import com.rbc.common.controller.BaseController;
import com.rbc.common.utils.PageUtils;
import com.rbc.common.utils.Query;
import com.rbc.common.utils.R;
import com.rbc.system.domain.UserDO;
import com.rbc.system.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 工具检视信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:05
 */
 
@Controller
@RequestMapping("/biz/toolInspection")
public class ToolInspectionController extends BaseController {
	@Autowired
	private ToolInspectionService toolInspectionService;
	@Autowired
	WenchService wenchService;
	@Autowired
	ToolOtherService toolOtherService;
	@Autowired
	UserService userService;
	@GetMapping()
	@RequiresPermissions("biz:toolInspection:toolInspection")
	String ToolInspection(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/toolInspection/toolInspection";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:toolInspection:toolInspection")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ToolInspectionDO> toolInspectionList = toolInspectionService.list(query);
		int total = toolInspectionService.count(query);
		PageUtils pageUtils = new PageUtils(toolInspectionList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listNew")
	@RequiresPermissions("biz:toolInspection:toolInspection")
	public PageUtils listNew(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ToolInspectionDO> toolInspectionList = toolInspectionService.list(query);
		int total = toolInspectionService.count(query);
		PageUtils pageUtils = new PageUtils(toolInspectionList, total);
		return pageUtils;
	}
	@GetMapping("/add")
	@RequiresPermissions("biz:toolInspection:add")
	String add(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/toolInspection/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:toolInspection:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ToolInspectionDO toolInspection = toolInspectionService.get(id);
		model.addAttribute("toolInspection", toolInspection);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/toolInspection/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions(value = {"biz:toolInspection:add","biz:toolInspection:edit"},logical = Logical.OR)
	public R save( @RequestBody JSONObject jsonParam){
		System.out.println(jsonParam.toString());
		JSONObject toolInspection=jsonParam.getJSONObject("toolInspectionDo");
		JSONArray wench =jsonParam.getJSONArray("wenchDo");
		JSONArray toolOther =jsonParam.getJSONArray("toolOtherDo");

		ToolInspectionDO toolInspectionDo=JSON.parseObject(toolInspection.toJSONString(),ToolInspectionDO.class);

		Type typeWench = new TypeReference<List<WenchDO>>(){}.getType();
		List<WenchDO> wenchDoList = JSON.parseObject(wench.toJSONString(), typeWench);

		Type typeOtherTool = new TypeReference<List<ToolOtherDO>>(){}.getType();
		List<ToolOtherDO> toolOtherDoList = JSON.parseObject(toolOther.toJSONString(), typeOtherTool);

		int r=toolInspectionService.batchSaveOrUpdate(toolInspectionDo,wenchDoList,toolOtherDoList);

		if(r>0){
			return R.ok();
		}

		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:toolInspection:edit")
	public R update( ToolInspectionDO toolInspection){
		toolInspectionService.update(toolInspection);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:toolInspection:remove")
	public R remove( Long id){
		if(toolInspectionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:toolInspection:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		toolInspectionService.batchRemove(ids);
		return R.ok();
	}
	@ResponseBody
	@GetMapping("/listWench")
	@RequiresPermissions("biz:toolInspection:toolInspection")
	public PageUtils listWench(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<WenchDO> wenchList = wenchService.list(query);
		int total = wenchService.count(query);
		PageUtils pageUtils = new PageUtils(wenchList, total);
		return pageUtils;
	}


	@ResponseBody
	@GetMapping("/listOtherTool")
	@RequiresPermissions("biz:toolInspection:toolInspection")
	public PageUtils listOtherTool(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ToolOtherDO> otherToolList = toolOtherService.list(query);
		int total = toolOtherService.count(query);
		PageUtils pageUtils = new PageUtils(otherToolList, total);
		return pageUtils;
	}

	@ResponseBody
	@PostMapping("/batchRemoveOtherTool")
	@RequiresPermissions("biz:toolInspection:toolInspection")
	public R batchRemoveOtherTool(@RequestParam("ids[]") Long[] ids){
		toolOtherService.batchRemove(ids);
		return R.ok();
	}
	@ResponseBody
	@PostMapping("/batchRemoveWench")
	@RequiresPermissions("biz:toolInspection:toolInspection")
	public R batchRemoveWench(@RequestParam("ids[]") Long[] ids){
		wenchService.batchRemove(ids);
		return R.ok();
	}
}
