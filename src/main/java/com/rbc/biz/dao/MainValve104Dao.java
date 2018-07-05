package com.rbc.biz.dao;


import com.rbc.biz.domain.MainValve104DO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 104主阀信息表
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-04 16:55:57
 */
@Mapper
public interface MainValve104Dao {

	MainValve104DO get(Long id);
	
	List<MainValve104DO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MainValve104DO mainvalve);
	
	int update(MainValve104DO mainvalve);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
