package com.rbc.biz.dao;

import com.rbc.biz.domain.TaskInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 任务信息表
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
@Mapper
public interface TaskInfoDao {

	TaskInfoDO get(Long id);
	
	List<TaskInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TaskInfoDO taskInfo);
	
	int update(TaskInfoDO taskInfo);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
