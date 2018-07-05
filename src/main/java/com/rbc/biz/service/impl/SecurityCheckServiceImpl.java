package com.rbc.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.SecurityCheckDao;
import com.rbc.biz.domain.SecurityCheckDO;
import com.rbc.biz.service.SecurityCheckService;



@Service
public class SecurityCheckServiceImpl implements SecurityCheckService {
	@Autowired
	private SecurityCheckDao securityCheckDao;
	
	@Override
	public SecurityCheckDO get(Long id){
		return securityCheckDao.get(id);
	}
	
	@Override
	public List<SecurityCheckDO> list(Map<String, Object> map){
		return securityCheckDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return securityCheckDao.count(map);
	}
	
	@Override
	public int save(SecurityCheckDO securityCheck){
		return securityCheckDao.save(securityCheck);
	}
	
	@Override
	public int update(SecurityCheckDO securityCheck){
		return securityCheckDao.update(securityCheck);
	}
	
	@Override
	public int remove(Long id){
		return securityCheckDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return securityCheckDao.batchRemove(ids);
	}
	
}
