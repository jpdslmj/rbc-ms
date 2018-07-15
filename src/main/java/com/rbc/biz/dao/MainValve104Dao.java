package com.rbc.biz.dao;

import com.rbc.biz.domain.MainValve104DO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 104主阀信息表
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-07 10:22:40
 */
@Mapper
public interface MainValve104Dao {

	MainValve104DO get(Long id);
	
	List<MainValve104DO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MainValve104DO mainValve104);
	
	int update(MainValve104DO mainValve104);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
