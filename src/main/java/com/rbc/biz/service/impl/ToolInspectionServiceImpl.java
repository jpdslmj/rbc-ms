package com.rbc.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.ToolInspectionDao;
import com.rbc.biz.domain.ToolInspectionDO;
import com.rbc.biz.service.ToolInspectionService;



@Service
public class ToolInspectionServiceImpl implements ToolInspectionService {
	@Autowired
	private ToolInspectionDao toolInspectionDao;
	
	@Override
	public ToolInspectionDO get(Long id){
		return toolInspectionDao.get(id);
	}
	
	@Override
	public List<ToolInspectionDO> list(Map<String, Object> map){
		return toolInspectionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return toolInspectionDao.count(map);
	}
	
	@Override
	public int save(ToolInspectionDO toolInspection){
		return toolInspectionDao.save(toolInspection);
	}
	
	@Override
	public int update(ToolInspectionDO toolInspection){
		return toolInspectionDao.update(toolInspection);
	}
	
	@Override
	public int remove(Long id){
		return toolInspectionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return toolInspectionDao.batchRemove(ids);
	}
	
}
