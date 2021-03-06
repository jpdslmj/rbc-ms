package com.rbc.biz.service;

import com.rbc.biz.domain.TaskDistributionDO;

import java.util.List;
import java.util.Map;

/**
 * 任务发布表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
public interface TaskDistributionService {
	
	TaskDistributionDO get(Long id);
	
	List<TaskDistributionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TaskDistributionDO taskDistribution);
	
	int update(TaskDistributionDO taskDistribution);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
