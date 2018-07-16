package com.rbc.biz.service.impl;

import com.rbc.activiti.config.ActivitiConstant;
import com.rbc.activiti.service.impl.ActTaskServiceImpl;
import com.rbc.biz.dao.PopValve104Dao;
import com.rbc.biz.domain.PopValve104DO;
import com.rbc.biz.service.PopValve104Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class PopValve104ServiceImpl implements PopValve104Service {
	@Autowired
	private PopValve104Dao popValve104Dao;

	@Autowired
	private ActTaskServiceImpl actTaskService;

	@Override
	public PopValve104DO get(Long id){
		return popValve104Dao.get(id);
	}
	
	@Override
	public List<PopValve104DO> list(Map<String, Object> map){
		return popValve104Dao.list(map);
	}

	@Override
	public List<PopValve104DO> taskList(Map<String, Object> map) {
		return popValve104Dao.taskList(map);
	}

	@Override
	public int taskCount(Map<String, Object> map) {
		return popValve104Dao.taskCount(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return popValve104Dao.count(map);
	}
	
	@Override
	public int save(PopValve104DO popValve104){
		int i = popValve104Dao.save(popValve104);
		actTaskService.startProcess(ActivitiConstant.ACTIVITI_PROCESS104[0],ActivitiConstant.ACTIVITI_PROCESS104[1],popValve104.getId().toString(),null,new HashMap<>());
		return i;
	}
	
	@Override
	public int update(PopValve104DO popValve104){
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("pass",  popValve104.getTaskPass());
		vars.put("title","");
		actTaskService.complete(popValve104.getTaskId(),vars);
		return popValve104Dao.update(popValve104);
	}
	
	@Override
	public int remove(Long id){
		return popValve104Dao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return popValve104Dao.batchRemove(ids);
	}

}
