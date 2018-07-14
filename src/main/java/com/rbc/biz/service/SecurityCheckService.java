package com.rbc.biz.service;

import com.rbc.biz.domain.SecurityCheckDO;

import java.util.List;
import java.util.Map;

/**
 * 安全质量检查表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
public interface SecurityCheckService {
	
	SecurityCheckDO get(Long id);
	
	List<SecurityCheckDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SecurityCheckDO securityCheck);
	
	int update(SecurityCheckDO securityCheck);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	//int batchSaveOrUpdate(List<SecurityCheckDO> list);
}
