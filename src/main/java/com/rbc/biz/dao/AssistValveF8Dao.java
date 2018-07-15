package com.rbc.biz.dao;

import com.rbc.biz.domain.AssistValveF8DO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * F8辅助阀信息表
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-07 10:22:40
 */
@Mapper
public interface AssistValveF8Dao {

	AssistValveF8DO get(Long id);
	
	List<AssistValveF8DO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AssistValveF8DO assistValveF8);
	
	int update(AssistValveF8DO assistValveF8);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
