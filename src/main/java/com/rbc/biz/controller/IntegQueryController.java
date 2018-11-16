package com.rbc.biz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbc.biz.domain.AssistValveF8DO;
import com.rbc.biz.domain.MainValve104DO;
import com.rbc.biz.domain.MainValveF8DO;
import com.rbc.biz.domain.PopValve104DO;
import com.rbc.biz.service.AssistValveF8Service;
import com.rbc.biz.service.MainValve104Service;
import com.rbc.biz.service.MainValveF8Service;
import com.rbc.biz.service.PopValve104Service;
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

import com.rbc.common.utils.PageUtils;
import com.rbc.common.utils.Query;
import com.rbc.common.utils.R;

/**
 * 综合查询
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-11-10 17:21:22
 */
 
@Controller
@RequestMapping("/biz/IntegQuery")
public class IntegQueryController {
	@Autowired
	private AssistValveF8Service assistValveF8Service;
	@Autowired
	private PopValve104Service popValve104Service;
	@Autowired
	private MainValve104Service mainValve104Service;
	@Autowired
	private MainValveF8Service mainValveF8Service;
	@Autowired
	private UserService userService;

	@GetMapping()
	@RequiresPermissions("biz:IntegQuery:IntegQuery")
	String IntegQuery(){
	    return "biz/IntegQuery/IntegQuery";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:IntegQuery:IntegQuery")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		int valueType= Integer.parseInt(params.get("valueType").toString());
		int total = 0;
		PageUtils pageUtils = new PageUtils(null, total);
		String valueNo=query.get("valueNo").toString();

		switch(valueType){
			case 0:
			{
				total = 0;
				pageUtils = new PageUtils(null, total);
				break;
			}
			case 1://104紧急阀
			{   query.put("popValue",valueNo);
				List<PopValve104DO> popValve104List = popValve104Service.list(query);
				total = popValve104Service.count(query);
				pageUtils = new PageUtils(popValve104List, total);
				break;
			}
			case 2://104主阀
			{   query.put("popValue",valueNo);
				List<MainValve104DO> mainValve104List = mainValve104Service.list(query);
				total = mainValve104Service.count(query);
				pageUtils = new PageUtils(mainValve104List, total);
				break;
			}
			case 3://F8主阀
			{   query.put("popValue",valueNo);
				List<MainValveF8DO> mainValveF8List = mainValveF8Service.list(query);
				total = mainValveF8Service.count(query);
				pageUtils = new PageUtils(mainValveF8List, total);
				break;
			}
			case 4://F8辅助阀
			{   query.put("assistValue",valueNo);
				List<AssistValveF8DO> assistValveF8List = assistValveF8Service.list(query);
				total = assistValveF8Service.count(query);
				pageUtils = new PageUtils(assistValveF8List, total);
				break;
			}

		}
		return pageUtils;
	}
	@GetMapping("/print/{popValue}/{valueType}")
	public String print(@PathVariable("popValue") String popValue,@PathVariable("valueType") int  valueType,Model model){
		String returnUrl="";
		switch (valueType){
			case 1:
			{
				HashMap map = new HashMap<>();
				map.put("popValue",popValue);
				List<PopValve104DO> popValve104List = popValve104Service.list(map);
				model.addAttribute("popValve104", popValve104List.get(0));
				returnUrl="biz/IntegQuery/popValve104Print";
				break;
			}
			case 2:
			{
				HashMap map = new HashMap<>();
				map.put("popValue",popValue);
				List<MainValve104DO> mainValve104List = mainValve104Service.list(map);
				model.addAttribute("mainValve104", mainValve104List.get(0));
				returnUrl="biz/IntegQuery/mainValve104Print";
				break;
			}
			case 3:
			{
				HashMap map = new HashMap<>();
				map.put("popValue",popValue);
				List<MainValveF8DO> mainValveF8List = mainValveF8Service.list(map);
				model.addAttribute("mainValveF8", mainValveF8List.get(0));
				returnUrl="biz/IntegQuery/mainValveF8Print";
				break;
			}
			case 4:
			{
				HashMap map = new HashMap<>();
				map.put("popValue",popValue);
				List<AssistValveF8DO> assistValveF8List = assistValveF8Service.list(map);
				model.addAttribute("assistValveF8", assistValveF8List.get(0));
				returnUrl="biz/IntegQuery/assitValveF8Print";
				break;
			}

		}
		return returnUrl;
	}
	/**

	 @GetMapping("/add")
	@RequiresPermissions("biz:IntegQuery:add")
	String add(){
	    return "biz/IntegQuery/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:IntegQuery:edit")
	String edit(@PathVariable("id") Long id,Model model){
		IntegQueryDO IntegQuery = IntegQueryService.get(id);
		model.addAttribute("IntegQuery", IntegQuery);
	    return "biz/IntegQuery/edit";
	}
	*/
	/**
	 * 保存

	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:IntegQuery:add")
	public R save( IntegQueryDO IntegQuery){
		if(IntegQueryService.save(IntegQuery)>0){
			return R.ok();
		}
		return R.error();
	}
	 */
	/**
	 * 修改

	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:IntegQuery:edit")
	public R update( IntegQueryDO IntegQuery){
		IntegQueryService.update(IntegQuery);
		return R.ok();
	}
	 */
	/**
	 * 删除

	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:IntegQuery:remove")
	public R remove( Long id){
		if(IntegQueryService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	 */
	/**
	 * 删除

	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:IntegQuery:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		IntegQueryService.batchRemove(ids);
		return R.ok();
	}
	 */
}
