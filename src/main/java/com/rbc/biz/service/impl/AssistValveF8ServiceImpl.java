package com.rbc.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.AssistValveF8Dao;
import com.rbc.biz.domain.AssistValveF8DO;
import com.rbc.biz.service.AssistValveF8Service;



@Service
public class AssistValveF8ServiceImpl implements AssistValveF8Service {
	@Autowired
	private AssistValveF8Dao assistValveF8Dao;
	
	@Override
	public AssistValveF8DO get(Long id){
		return assistValveF8Dao.get(id);
	}
	
	@Override
	public List<AssistValveF8DO> list(Map<String, Object> map){
		return assistValveF8Dao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return assistValveF8Dao.count(map);
	}
	
	@Override
	public int save(AssistValveF8DO assistValveF8){
		return assistValveF8Dao.save(assistValveF8);
	}
	
	@Override
	public int update(AssistValveF8DO assistValveF8){
		return assistValveF8Dao.update(assistValveF8);
	}
	
	@Override
	public int remove(Long id){
		return assistValveF8Dao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return assistValveF8Dao.batchRemove(ids);
	}
	
}
