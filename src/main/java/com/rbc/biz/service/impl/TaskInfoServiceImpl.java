package com.rbc.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.TaskInfoDao;
import com.rbc.biz.domain.TaskInfoDO;
import com.rbc.biz.service.TaskInfoService;



@Service
public class TaskInfoServiceImpl implements TaskInfoService {
	@Autowired
	private TaskInfoDao taskInfoDao;
	
	@Override
	public TaskInfoDO get(Long id){
		return taskInfoDao.get(id);
	}
	
	@Override
	public List<TaskInfoDO> list(Map<String, Object> map){
		return taskInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return taskInfoDao.count(map);
	}
	
	@Override
	public int save(TaskInfoDO taskInfo){
		return taskInfoDao.save(taskInfo);
	}
	
	@Override
	public int update(TaskInfoDO taskInfo){
		return taskInfoDao.update(taskInfo);
	}
	
	@Override
	public int remove(Long id){
		return taskInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return taskInfoDao.batchRemove(ids);
	}
	
}
