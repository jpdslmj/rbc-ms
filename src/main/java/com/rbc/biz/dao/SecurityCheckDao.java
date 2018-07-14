package com.rbc.biz.dao;

import com.rbc.biz.domain.SecurityCheckDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 安全质量检查表
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
@Mapper
public interface SecurityCheckDao {

	SecurityCheckDO get(Long id);
	
	List<SecurityCheckDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SecurityCheckDO securityCheck);
	
	int update(SecurityCheckDO securityCheck);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int  batchSave(List<SecurityCheckDO> list);
	int  batchUpdate(List<SecurityCheckDO> list);
}
