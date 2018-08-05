package com.rbc.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.MainValveF8Dao;
import com.rbc.biz.domain.MainValveF8DO;
import com.rbc.biz.service.MainValveF8Service;



@Service
public class MainValveF8ServiceImpl implements MainValveF8Service {
	@Autowired
	private MainValveF8Dao mainValveF8Dao;
	
	@Override
	public MainValveF8DO get(Long id){
		return mainValveF8Dao.get(id);
	}
	
	@Override
	public List<MainValveF8DO> list(Map<String, Object> map){
		return mainValveF8Dao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return mainValveF8Dao.count(map);
	}
	
	@Override
	public int save(MainValveF8DO mainValveF8){
		return mainValveF8Dao.save(mainValveF8);
	}
	
	@Override
	public int update(MainValveF8DO mainValveF8){
		return mainValveF8Dao.update(mainValveF8);
	}
	
	@Override
	public int remove(Long id){
		return mainValveF8Dao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return mainValveF8Dao.batchRemove(ids);
	}

	@Override
	public boolean exit(Map<String, Object> params) {
		boolean exit;
		exit = mainValveF8Dao.list(params).size() > 0;
		return exit;
	}
	
}
