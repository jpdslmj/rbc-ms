package com.rbc.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.TestToolDao;
import com.rbc.biz.domain.TestToolDO;
import com.rbc.biz.service.TestToolService;



@Service
public class TestToolServiceImpl implements TestToolService {
	@Autowired
	private TestToolDao testToolDao;
	
	@Override
	public TestToolDO get(Long id){
		return testToolDao.get(id);
	}
	
	@Override
	public List<TestToolDO> list(Map<String, Object> map){
		return testToolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return testToolDao.count(map);
	}
	
	@Override
	public int save(TestToolDO testTool){
		return testToolDao.save(testTool);
	}
	
	@Override
	public int update(TestToolDO testTool){
		return testToolDao.update(testTool);
	}
	
	@Override
	public int remove(Long id){
		return testToolDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return testToolDao.batchRemove(ids);
	}
	
}
