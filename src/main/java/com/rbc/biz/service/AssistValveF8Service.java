package com.rbc.biz.service;

import com.rbc.biz.domain.AssistValveF8DO;

import java.util.List;
import java.util.Map;

/**
 * F8辅助阀信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-23 00:13:25
 */
public interface AssistValveF8Service {
	
	AssistValveF8DO get(Long id);
	
	List<AssistValveF8DO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AssistValveF8DO assistValveF8);
	
	int update(AssistValveF8DO assistValveF8);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	boolean exit(Map<String, Object> params);
}
