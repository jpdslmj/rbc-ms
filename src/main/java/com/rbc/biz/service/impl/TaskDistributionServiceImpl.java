package com.rbc.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.TaskDistributionDao;
import com.rbc.biz.domain.TaskDistributionDO;
import com.rbc.biz.service.TaskDistributionService;



@Service
public class TaskDistributionServiceImpl implements TaskDistributionService {
	@Autowired
	private TaskDistributionDao taskDistributionDao;
	
	@Override
	public TaskDistributionDO get(Long id){
		return taskDistributionDao.get(id);
	}
	
	@Override
	public List<TaskDistributionDO> list(Map<String, Object> map){
		return taskDistributionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return taskDistributionDao.count(map);
	}
	
	@Override
	public int save(TaskDistributionDO taskDistribution){
		return taskDistributionDao.save(taskDistribution);
	}
	
	@Override
	public int update(TaskDistributionDO taskDistribution){
		return taskDistributionDao.update(taskDistribution);
	}
	
	@Override
	public int remove(Long id){
		return taskDistributionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return taskDistributionDao.batchRemove(ids);
	}
	
}
