package com.rbc.biz.service;

import com.rbc.biz.domain.PopValve104DO;

import java.util.List;
import java.util.Map;

/**
 * 104紧急阀信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
public interface PopValve104Service {
	
	PopValve104DO get(Long id);
	
	List<PopValve104DO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	List<PopValve104DO> taskList(Map<String, Object> map);
	
	int taskCount(Map<String, Object> map);
	
	int save(PopValve104DO popValve104);
	
	int update(PopValve104DO popValve104);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	boolean exit(Map<String, Object> params);
}
