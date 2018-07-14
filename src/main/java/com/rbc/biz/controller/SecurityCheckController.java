package com.rbc.biz.controller;
import com.rbc.biz.domain.SecurityCheckDO;
import com.rbc.biz.service.SecurityCheckService;
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
 * 安全质量检查表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
 
@Controller
@RequestMapping("/biz/securityCheck")
public class SecurityCheckController {
	@Autowired
	private SecurityCheckService securityCheckService;
	
	@GetMapping()
	@RequiresPermissions("biz:securityCheck:securityCheck")
	String SecurityCheck(){
	    return "biz/securityCheck/securityCheck";
	}
	
	/**@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:securityCheck:securityCheck")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SecurityCheckDO> securityCheckList = securityCheckService.list(query);
		int total = securityCheckService.count(query);
		PageUtils pageUtils = new PageUtils(securityCheckList, total);
		return pageUtils;
	}*/
	
	@GetMapping("/add")
	@RequiresPermissions("biz:securityCheck:add")
	String add(){
	    return "biz/securityCheck/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("biz:securityCheck:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SecurityCheckDO securityCheck = securityCheckService.get(id);
		model.addAttribute("securityCheck", securityCheck);
	    return "biz/securityCheck/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:securityCheck:add")
	public R save( SecurityCheckDO securityCheck){
		if(securityCheckService.save(securityCheck)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:securityCheck:edit")
	public R update( SecurityCheckDO securityCheck){
		securityCheckService.update(securityCheck);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:securityCheck:remove")
	public R remove( Long id){
		if(securityCheckService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:securityCheck:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		securityCheckService.batchRemove(ids);
		return R.ok();
	}
	
}
