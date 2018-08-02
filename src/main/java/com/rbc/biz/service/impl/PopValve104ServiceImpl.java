package com.rbc.biz.service.impl;

import com.rbc.activiti.config.ActivitiConstant;
import com.rbc.activiti.service.impl.ActTaskServiceImpl;
import com.rbc.biz.dao.PopValve104Dao;
import com.rbc.biz.domain.PopValve104DO;
import com.rbc.biz.service.PopValve104Service;
import com.rbc.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class PopValve104ServiceImpl implements PopValve104Service {
	@Autowired
	private PopValve104Dao popValve104Dao;

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
		return popValve104Dao.save(popValve104);
	}
	
	@Override
	public int update(PopValve104DO popValve104){
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
