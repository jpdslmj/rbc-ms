package com.rbc.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.OptionsDao;
import com.rbc.biz.domain.OptionsDO;
import com.rbc.biz.service.OptionsService;



@Service
public class OptionsServiceImpl implements OptionsService {
	@Autowired
	private OptionsDao optionsDao;
	
	@Override
	public OptionsDO get(Integer id){
		return optionsDao.get(id);
	}
	
	@Override
	public List<OptionsDO> list(Map<String, Object> map){
		return optionsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return optionsDao.count(map);
	}
	
	@Override
	public int save(OptionsDO options){
		return optionsDao.save(options);
	}
	
	@Override
	public int update(OptionsDO options){
		return optionsDao.update(options);
	}
	
	@Override
	public int remove(Integer id){
		return optionsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return optionsDao.batchRemove(ids);
	}
	
}
