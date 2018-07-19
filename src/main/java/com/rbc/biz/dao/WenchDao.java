package com.rbc.biz.dao;

import com.rbc.biz.domain.WenchDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 问题扳手信息表
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:05
 */
@Mapper
public interface WenchDao {

	WenchDO get(Long id);
	
	List<WenchDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WenchDO wench);
	
	int update(WenchDO wench);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	int  batchSave(List<WenchDO> list);
	int  batchUpdate(List<WenchDO> list);
}
