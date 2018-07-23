package com.rbc.biz.dao;

import com.rbc.biz.domain.OptionsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 任务项目表
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-21 10:57:36
 */
@Mapper
public interface OptionsDao {

	OptionsDO get(Integer id);
	
	List<OptionsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OptionsDO options);
	
	int update(OptionsDO options);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
