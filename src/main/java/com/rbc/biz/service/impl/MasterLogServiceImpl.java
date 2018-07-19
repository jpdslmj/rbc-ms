package com.rbc.biz.service.impl;

import com.rbc.biz.dao.SecurityCheckDao;
import com.rbc.biz.domain.SecurityCheckDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.MasterLogDao;
import com.rbc.biz.domain.MasterLogDO;
import com.rbc.biz.service.MasterLogService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MasterLogServiceImpl implements MasterLogService {
	@Autowired
	private MasterLogDao masterLogDao;
	@Autowired
	private SecurityCheckDao securityCheckDao;
	@Override
	public MasterLogDO get(Long id){
		return masterLogDao.get(id);
	}
	
	@Override
	public List<MasterLogDO> list(Map<String, Object> map){
		return masterLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return masterLogDao.count(map);
	}
	
	@Override
	public int save(MasterLogDO masterLog){
		return masterLogDao.save(masterLog);
	}
	
	@Override
	public int update(MasterLogDO masterLog){
		return masterLogDao.update(masterLog);
	}
	
	@Override
	public int remove(Long id){
		return masterLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return masterLogDao.batchRemove(ids);
	}

	@Transactional
	@Override
    public int  batchSaveOrUpdate(MasterLogDO masterLogDo,List<SecurityCheckDO>securityCheckList){

		List<SecurityCheckDO> saveList=new ArrayList();
        long logId=0;
        int l =0;
		int j =0;
        if("".equals(masterLogDo.getId())||masterLogDo.getId()==null){
			masterLogDo.setCreateTime(new Date());
			masterLogDo.setUpdateTime(new Date());
			l=save(masterLogDo);
			if(l>0){
				logId=masterLogDo.getId();
			}
		}else{
			logId=masterLogDo.getId();
			securityCheckDao.remove(logId);
			masterLogDo.setUpdateTime(new Date());
			j=update(masterLogDo);
		}

		for(int i=0;i<securityCheckList.size();i++){
			if("".equals(securityCheckList.get(i).getId())||securityCheckList.get(i).getId()==null){
                SecurityCheckDO securityCheck =securityCheckList.get(i);
                securityCheck.setLogId(logId);
                securityCheck.setCreateTime(new Date());
                securityCheck.setUpdateTime(new Date());
                securityCheck.setGangmasterNo(masterLogDo.getGangmasterNo());
                securityCheck.setGangmasterName(masterLogDo.getGangmasterName());
                saveList.add(securityCheck);
			}else{
				SecurityCheckDO securityUpCheck =securityCheckList.get(i);
				securityUpCheck.setUpdateTime(new Date());
				securityUpCheck.setId(null);
				saveList.add(securityUpCheck);
			}

		}
		int k=0;
		if(saveList!=null&&saveList.size()>0){j=securityCheckDao.batchSave(saveList);}
		return l+j+k;
	};
	
}
