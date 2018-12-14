package com.rbc.biz.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbc.biz.domain.*;
import com.rbc.biz.service.*;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rbc.common.utils.PageUtils;
import com.rbc.common.utils.Query;
import com.rbc.common.utils.R;

/**
 * 试验部件信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-08-09 22:48:45
 */
 
@Controller
@RequestMapping("/biz/testParts")
public class TestPartsController extends BaseController {
	@Autowired
	private TestPartsService testPartsService;
	@Autowired
	UserService userService;
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
		UserDO userDO  = userService.get(getUserId());
		query.put("testerNo",userDO.getUsername());
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
	@Autowired
	private PopValve104Service popValve104Service;
	@Autowired
	private MainValve104Service mainValve104Service;
	@Autowired
	private AssistValveF8Service assistValveF8Service;
	@Autowired
	private MainValveF8Service mainValveF8Service;
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:testParts:add")
	public R save( TestPartsDO testParts){
		UserDO userDO  = userService.get(getUserId());
		String testPartType=testParts.getTestPartType();
		Map paraMap=new HashMap<String,Object> ();
		Object testPartNo= testParts.getTestPartNo();
		String assemberNo="";
		String assemblerName="";
		Date assemberTime=new Date();
		switch (testPartType){
			case "1":{
				paraMap.put("popValue",testPartNo);
				List<PopValve104DO> popValve104List=popValve104Service.list(paraMap);
				if(popValve104List!=null&&popValve104List.size()>0){
					assemberNo=popValve104List.get(0).getAssemblerNo();
					assemblerName=popValve104List.get(0).getAssemblerName();
					assemberTime=popValve104List.get(0).getCreateTime();
				}
				break;
			}
			case "2":{
				paraMap.put("popValue",testPartNo);
				List<MainValve104DO> mainValve104List=mainValve104Service.list(paraMap);
				if(mainValve104List!=null&&mainValve104List.size()>0){
					assemberNo=mainValve104List.get(0).getAssemblerNo();
					assemblerName=mainValve104List.get(0).getAssemblerName();
					assemberTime=mainValve104List.get(0).getCreateTime();
				}
				break;
			}
			case "3":{
				paraMap.put("popValue",testPartNo);
				List<MainValveF8DO> mainValve104List=mainValveF8Service.list(paraMap);
				if(mainValve104List!=null&&mainValve104List.size()>0){
					assemberNo=mainValve104List.get(0).getAssemblerNo();
					assemblerName=mainValve104List.get(0).getAssemblerName();
					assemberTime=mainValve104List.get(0).getCreateTime();
				}

				break;
			}
			case "4":{
				paraMap.put("popValue",testPartNo);
				List<AssistValveF8DO> assistValve104List=assistValveF8Service.list(paraMap);
				if(assistValve104List!=null&&assistValve104List.size()>0){
					assemberNo=assistValve104List.get(0).getAssemblerNo();
					assemblerName=assistValve104List.get(0).getAssemblerName();
					assemberTime=assistValve104List.get(0).getCreateTime();
				}
				break;
			}
		}
		testParts.setAssemblerNo(assemberNo);
		testParts.setAssemblerName(assemblerName);
		testParts.setAssembleDate(assemberTime);
		testParts.setTesterNo(userDO.getUsername());
		testParts.setTesterName(userDO.getName());
		testParts.setCreateTime(new Date());
		testParts.setUpdateTime(new Date());
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
		UserDO userDO  = userService.get(getUserId());
		String testPartType=testParts.getTestPartType();
		Map paraMap=new HashMap<String,Object> ();
		Object testPartNo= testParts.getTestPartNo();
		String assemberNo="";
		String assemblerName="";
		Date assemberTime=new Date();
		switch (testPartType){
			case "1":{
				paraMap.put("popValue",testPartNo);
				List<PopValve104DO> popValve104List=popValve104Service.list(paraMap);
				if(popValve104List!=null&&popValve104List.size()>0){
					assemberNo=popValve104List.get(0).getAssemblerNo();
					assemblerName=popValve104List.get(0).getAssemblerName();
					assemberTime=popValve104List.get(0).getCreateTime();
				}
				break;
			}
			case "2":{
				paraMap.put("popValue",testPartNo);
				List<MainValve104DO> mainValve104List=mainValve104Service.list(paraMap);
				if(mainValve104List!=null&&mainValve104List.size()>0){
					assemberNo=mainValve104List.get(0).getAssemblerNo();
					assemblerName=mainValve104List.get(0).getAssemblerName();
					assemberTime=mainValve104List.get(0).getCreateTime();
				}
				break;
			}
			case "3":{
				paraMap.put("assistValue",testPartNo);
				List<AssistValveF8DO> assistValve104List=assistValveF8Service.list(paraMap);
				if(assistValve104List!=null&&assistValve104List.size()>0){
					assemberNo=assistValve104List.get(0).getAssemblerNo();
					assemblerName=assistValve104List.get(0).getAssemblerName();
					assemberTime=assistValve104List.get(0).getCreateTime();
				}
				break;
			}
			case "4":{
				paraMap.put("popValue",testPartNo);
				List<MainValveF8DO> mainValve104List=mainValveF8Service.list(paraMap);
				if(mainValve104List!=null&&mainValve104List.size()>0){
					assemberNo=mainValve104List.get(0).getAssemblerNo();
					assemblerName=mainValve104List.get(0).getAssemblerName();
					assemberTime=mainValve104List.get(0).getCreateTime();
				}
				break;
			}
		}
		testParts.setAssemblerNo(assemberNo);
		testParts.setAssemblerName(assemblerName);
		testParts.setAssembleDate(assemberTime);
		testParts.setTesterNo(userDO.getUsername());
		testParts.setTesterName(userDO.getName());
		testParts.setUpdateTime(new Date());
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
