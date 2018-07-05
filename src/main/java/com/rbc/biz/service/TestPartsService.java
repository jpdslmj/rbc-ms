package com.rbc.biz.service;

import com.rbc.biz.domain.TestPartsDO;

import java.util.List;
import java.util.Map;

/**
 * 试验部件信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
public interface TestPartsService {
	
	TestPartsDO get(Long id);
	
	List<TestPartsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TestPartsDO testParts);
	
	int update(TestPartsDO testParts);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
