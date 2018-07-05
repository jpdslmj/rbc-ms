package com.rbc.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.WenchDao;
import com.rbc.biz.domain.WenchDO;
import com.rbc.biz.service.WenchService;



@Service
public class WenchServiceImpl implements WenchService {
	@Autowired
	private WenchDao wenchDao;
	
	@Override
	public WenchDO get(Long id){
		return wenchDao.get(id);
	}
	
	@Override
	public List<WenchDO> list(Map<String, Object> map){
		return wenchDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return wenchDao.count(map);
	}
	
	@Override
	public int save(WenchDO wench){
		return wenchDao.save(wench);
	}
	
	@Override
	public int update(WenchDO wench){
		return wenchDao.update(wench);
	}
	
	@Override
	public int remove(Long id){
		return wenchDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return wenchDao.batchRemove(ids);
	}
	
}
