package com.rbc.biz.service;

import com.rbc.biz.domain.ToolOtherDO;

import java.util.List;
import java.util.Map;

/**
 * 其他问题工具信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:05
 */
public interface ToolOtherService {
	
	ToolOtherDO get(Long id);
	
	List<ToolOtherDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ToolOtherDO toolOther);
	
	int update(ToolOtherDO toolOther);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
