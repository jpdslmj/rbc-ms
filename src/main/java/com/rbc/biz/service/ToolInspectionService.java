package com.rbc.biz.service;

import com.rbc.biz.domain.ToolInspectionDO;
import com.rbc.biz.domain.ToolOtherDO;
import com.rbc.biz.domain.WenchDO;

import java.util.List;
import java.util.Map;

/**
 * 工具检视信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:05
 */
public interface ToolInspectionService {
	
	ToolInspectionDO get(Long id);
	
	List<ToolInspectionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ToolInspectionDO toolInspection);
	
	int update(ToolInspectionDO toolInspection);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int batchSaveOrUpdate(ToolInspectionDO toolInspectionDo,List<WenchDO> wenchDoList,List<ToolOtherDO> toolOtherDoList);
}
