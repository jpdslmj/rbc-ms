package com.rbc.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.TestPartsDao;
import com.rbc.biz.domain.TestPartsDO;
import com.rbc.biz.service.TestPartsService;



@Service
public class TestPartsServiceImpl implements TestPartsService {
	@Autowired
	private TestPartsDao testPartsDao;
	
	@Override
	public TestPartsDO get(Long id){
		return testPartsDao.get(id);
	}
	
	@Override
	public List<TestPartsDO> list(Map<String, Object> map){
		return testPartsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return testPartsDao.count(map);
	}
	
	@Override
	public int save(TestPartsDO testParts){
		return testPartsDao.save(testParts);
	}
	
	@Override
	public int update(TestPartsDO testParts){
		return testPartsDao.update(testParts);
	}
	
	@Override
	public int remove(Long id){
		return testPartsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return testPartsDao.batchRemove(ids);
	}
	
}
