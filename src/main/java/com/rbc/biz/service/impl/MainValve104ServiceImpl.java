package com.rbc.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.MainValve104Dao;
import com.rbc.biz.domain.MainValve104DO;
import com.rbc.biz.service.MainValve104Service;



@Service
public class MainValve104ServiceImpl implements MainValve104Service {
	@Autowired
	private MainValve104Dao mainValve104Dao;
	
	@Override
	public MainValve104DO get(Long id){
		return mainValve104Dao.get(id);
	}
	
	@Override
	public List<MainValve104DO> list(Map<String, Object> map){
		return mainValve104Dao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return mainValve104Dao.count(map);
	}
	
	@Override
	public int save(MainValve104DO mainValve104){
		return mainValve104Dao.save(mainValve104);
	}
	
	@Override
	public int update(MainValve104DO mainValve104){
		return mainValve104Dao.update(mainValve104);
	}
	
	@Override
	public int remove(Long id){
		return mainValve104Dao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return mainValve104Dao.batchRemove(ids);
	}

	@Override
	public boolean exit(Map<String, Object> params) {
		boolean exit;
		exit = mainValve104Dao.list(params).size() > 0;
		return exit;
	}
}
