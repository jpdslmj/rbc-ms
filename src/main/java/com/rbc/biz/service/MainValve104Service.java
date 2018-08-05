package com.rbc.biz.service;

import com.rbc.biz.domain.MainValve104DO;

import java.util.List;
import java.util.Map;

/**
 * 104主阀信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-23 00:13:25
 */
public interface MainValve104Service {
	
	MainValve104DO get(Long id);
	
	List<MainValve104DO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MainValve104DO mainValve104);
	
	int update(MainValve104DO mainValve104);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	boolean exit(Map<String, Object> params);
}
