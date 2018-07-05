package com.rbc.biz.service;

import com.rbc.biz.domain.TestToolDO;

import java.util.List;
import java.util.Map;

/**
 * 试验工具检视信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
public interface TestToolService {
	
	TestToolDO get(Long id);
	
	List<TestToolDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TestToolDO testTool);
	
	int update(TestToolDO testTool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
