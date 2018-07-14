package com.rbc.biz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rbc.biz.domain.MasterLogDO;
import com.rbc.biz.service.MasterLogService;
import com.rbc.biz.service.SecurityCheckService;
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
import com.rbc.biz.domain.SecurityCheckDO;

import java.lang.reflect.Type;
import java.util.*;

/**
 * 工长日志表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
 
@Controller
@RequestMapping("/biz/masterLog")
public class MasterLogController extends BaseController {
	@Autowired
	private MasterLogService masterLogService;

	@Autowired
	private SecurityCheckService securityCheckService;

	@Autowired
	private UserService userService;

	@GetMapping()
	@RequiresPermissions("biz:masterLog:masterLog")
	String MasterLog(){
	    return "biz/masterLog/masterLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:masterLog:masterLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MasterLogDO> masterLogList = masterLogService.list(query);
		int total = masterLogService.count(query);
		PageUtils pageUtils = new PageUtils(masterLogList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listSecurity")
	@RequiresPermissions("biz:securityCheck:query")
	public PageUtils listSecurity(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SecurityCheckDO> securityCheckList = securityCheckService.list(query);
		int total = securityCheckService.count(query);
		PageUtils pageUtils = new PageUtils(securityCheckList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("biz:masterLog:add")
	String add(Model model){
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		return "biz/masterLog/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:masterLog:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MasterLogDO masterLog = masterLogService.get(id);
		model.addAttribute("masterLog", masterLog);
	    return "biz/masterLog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:masterLog:add")
	public R save( MasterLogDO masterLog){
		if(masterLogService.save(masterLog)>0){
			long logId=masterLog.getId();
         Map<String,Object> map=new HashMap();
			map.put("code", 0);
			map.put("msg", "操作成功!");
			map.put("id",logId);
			return R.ok(map);
		}
		return R.error();
	}

	@ResponseBody
	@RequestMapping(value = "/saveSecurityCheck", method = RequestMethod.POST)
	@RequiresPermissions("biz:securityCheck:add")
	public R saveSecurityCheck(@RequestBody JSONObject jsonParam){

		System.out.println(jsonParam.toString());
		JSONObject masterLog=jsonParam.getJSONObject("masterLogDo");
		JSONArray securityChecks =jsonParam.getJSONArray("securityCheckDos");

		MasterLogDO masterLogDo=JSON.parseObject(masterLog.toJSONString(),MasterLogDO.class);

		Type type = new TypeReference<List<SecurityCheckDO>>(){}.getType();
		List<SecurityCheckDO> securityCheckList = JSON.parseObject(securityChecks.toJSONString(), type);

		int r=masterLogService.batchSaveOrUpdate(masterLogDo,securityCheckList);
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
	@RequiresPermissions("biz:masterLog:edit")
	public R update( MasterLogDO masterLog){
		masterLogService.update(masterLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:masterLog:remove")
	public R remove( Long id){
		if(masterLogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:masterLog:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		masterLogService.batchRemove(ids);
		return R.ok();
	}
	
}
