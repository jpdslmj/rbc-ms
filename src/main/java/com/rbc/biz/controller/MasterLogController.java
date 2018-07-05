package com.rbc.biz.controller;

import com.rbc.biz.domain.MasterLogDO;
import com.rbc.biz.service.MasterLogService;
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
 * 工长日志表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
 
@Controller
@RequestMapping("/biz/masterLog")
public class MasterLogController {
	@Autowired
	private MasterLogService masterLogService;
	
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
	
	@GetMapping("/add")
	@RequiresPermissions("biz:masterLog:add")
	String add(){
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
