package com.rbc.biz.service;

import com.rbc.biz.domain.MasterLogDO;
import com.rbc.biz.domain.SecurityCheckDO;

import java.util.List;
import java.util.Map;

/**
 * 工长日志表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
public interface MasterLogService {
	
	MasterLogDO get(Long id);
	
	List<MasterLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MasterLogDO masterLog);
	
	int update(MasterLogDO masterLog);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int batchSaveOrUpdate(MasterLogDO masterLogDo,List<SecurityCheckDO>securityCheckList);
}
