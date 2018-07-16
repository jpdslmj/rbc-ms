package com.rbc.biz.controller;

import com.rbc.activiti.domain.SalaryDO;
import com.rbc.activiti.utils.ActivitiUtils;
import com.rbc.activiti.vo.TaskVO;
import com.rbc.biz.domain.PopValve104DO;
import com.rbc.biz.service.PopValve104Service;
import com.rbc.common.controller.BaseController;
import com.rbc.common.utils.PageUtils;
import com.rbc.common.utils.Query;
import com.rbc.common.utils.R;
import com.rbc.system.domain.UserDO;
import com.rbc.system.service.RoleService;
import com.rbc.system.service.UserService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 104紧急阀信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
 
@Controller
@RequestMapping("/biz/popValve104")
public class PopValve104Controller extends BaseController {
	@Autowired
	private PopValve104Service popValve104Service;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
	@Autowired
    private ActivitiUtils activitiUtils;
    @Autowired
    private TaskService taskService;
	@GetMapping()
	@RequiresPermissions("biz:popValve104:popValve104")
	String PopValve104(){
	    return "biz/popValve104/popValve104";
	}

	@GetMapping("/history")
	//@RequiresPermissions("biz:popValve104:popValve104")
	String historyIndex(){
		return "biz/popValve104/history_index";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("biz:popValve104:popValve104")
	public PageUtils list(@RequestParam Map<String, Object> params){
        List<String> groupIds = roleService.getGroupIds(getUserId());
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroupIn(groupIds).list();
        List<String> taskIds =  new ArrayList<>();
        for(Task task : tasks){
            taskIds.add(task.getId());
        }
        List<PopValve104DO> popValve104List = null;
        int total = 0;
        if(taskIds.size() > 0) {
            //查询列表数据
            Query query = new Query(params);
            query.put("taskIds", taskIds);
            popValve104List = popValve104Service.taskList(query);
            total = popValve104Service.taskCount(query);
        } else {
            popValve104List = new ArrayList<PopValve104DO>();
        }
		PageUtils pageUtils = new PageUtils(popValve104List, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/history/list")
	@RequiresPermissions("biz:popValve104:popValve104")
	public PageUtils historyList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<PopValve104DO> popValve104List = popValve104Service.list(query);
		int total = popValve104Service.count(query);
		PageUtils pageUtils = new PageUtils(popValve104List, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("biz:popValve104:add")
    String add(Model model){
        UserDO userDO  = userService.get(getUserId());
        model.addAttribute("user",userDO);
        return "biz/popValve104/add";
    }

	@GetMapping("/edit/{taskId}")
	@RequiresPermissions("biz:popValve104:edit")
	String edit(@PathVariable("taskId") String taskId,Model model){
		PopValve104DO popValve104 = popValve104Service.get(Long.valueOf(activitiUtils.getBusinessKeyByTaskId(taskId)));
        popValve104.setTaskId(taskId);
		model.addAttribute("popValve104", popValve104);
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	    return "biz/popValve104/edit";
	}

	@GetMapping("/history/{id}")
	//@RequiresPermissions("biz:popValve104:edit")
	String historydetail(@PathVariable("id") Long id,Model model){
		PopValve104DO popValve104 = popValve104Service.get(id);
		model.addAttribute("popValve104", popValve104);
		return "biz/popValve104/history_detail";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("biz:popValve104:add")
	public R save( PopValve104DO popValve104){
		if(popValve104Service.save(popValve104)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("biz:popValve104:edit")
	public R update( PopValve104DO popValve104){
		popValve104Service.update(popValve104);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("biz:popValve104:remove")
	public R remove( Long id){
		if(popValve104Service.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("biz:popValve104:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		popValve104Service.batchRemove(ids);
		return R.ok();
	}

}
