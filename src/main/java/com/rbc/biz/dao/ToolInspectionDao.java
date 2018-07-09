package com.rbc.biz.dao;

import com.rbc.biz.domain.ToolInspectionDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 工具检视信息表
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:05
 */
@Mapper
public interface ToolInspectionDao {

	ToolInspectionDO get(Long id);
	
	List<ToolInspectionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ToolInspectionDO toolInspection);
	
	int update(ToolInspectionDO toolInspection);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
