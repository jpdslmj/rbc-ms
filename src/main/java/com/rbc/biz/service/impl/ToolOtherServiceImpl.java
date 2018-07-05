package com.rbc.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.ToolOtherDao;
import com.rbc.biz.domain.ToolOtherDO;
import com.rbc.biz.service.ToolOtherService;



@Service
public class ToolOtherServiceImpl implements ToolOtherService {
	@Autowired
	private ToolOtherDao toolOtherDao;
	
	@Override
	public ToolOtherDO get(Long id){
		return toolOtherDao.get(id);
	}
	
	@Override
	public List<ToolOtherDO> list(Map<String, Object> map){
		return toolOtherDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return toolOtherDao.count(map);
	}
	
	@Override
	public int save(ToolOtherDO toolOther){
		return toolOtherDao.save(toolOther);
	}
	
	@Override
	public int update(ToolOtherDO toolOther){
		return toolOtherDao.update(toolOther);
	}
	
	@Override
	public int remove(Long id){
		return toolOtherDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return toolOtherDao.batchRemove(ids);
	}
	
}
