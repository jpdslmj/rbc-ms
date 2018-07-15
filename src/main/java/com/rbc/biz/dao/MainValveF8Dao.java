package com.rbc.biz.dao;

import com.rbc.biz.domain.MainValveF8DO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * F8主阀信息表
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-07 10:22:40
 */
@Mapper
public interface MainValveF8Dao {

	MainValveF8DO get(Long id);
	
	List<MainValveF8DO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MainValveF8DO mainValveF8);
	
	int update(MainValveF8DO mainValveF8);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
