package com.rbc.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.MasterLogDao;
import com.rbc.biz.domain.MasterLogDO;
import com.rbc.biz.service.MasterLogService;



@Service
public class MasterLogServiceImpl implements MasterLogService {
	@Autowired
	private MasterLogDao masterLogDao;
	
	@Override
	public MasterLogDO get(Long id){
		return masterLogDao.get(id);
	}
	
	@Override
	public List<MasterLogDO> list(Map<String, Object> map){
		return masterLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return masterLogDao.count(map);
	}
	
	@Override
	public int save(MasterLogDO masterLog){
		return masterLogDao.save(masterLog);
	}
	
	@Override
	public int update(MasterLogDO masterLog){
		return masterLogDao.update(masterLog);
	}
	
	@Override
	public int remove(Long id){
		return masterLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return masterLogDao.batchRemove(ids);
	}
	
}
