package com.rbc.biz.service.impl;

import com.rbc.biz.dao.ToolOtherDao;
import com.rbc.biz.dao.WenchDao;
import com.rbc.biz.domain.ToolOtherDO;
import com.rbc.biz.domain.WenchDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rbc.biz.dao.ToolInspectionDao;
import com.rbc.biz.domain.ToolInspectionDO;
import com.rbc.biz.service.ToolInspectionService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ToolInspectionServiceImpl implements ToolInspectionService {
	@Autowired
	private ToolInspectionDao toolInspectionDao;
	@Autowired
	private WenchDao wenchDao;
	@Autowired
	private ToolOtherDao toolOtherDao;

	@Override
	public ToolInspectionDO get(Long id){
		return toolInspectionDao.get(id);
	}
	
	@Override
	public List<ToolInspectionDO> list(Map<String, Object> map){
		return toolInspectionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return toolInspectionDao.count(map);
	}
	
	@Override
	public int save(ToolInspectionDO toolInspection){
		return toolInspectionDao.save(toolInspection);
	}
	
	@Override
	public int update(ToolInspectionDO toolInspection){
		return toolInspectionDao.update(toolInspection);
	}

	@Transactional
	@Override
	public int remove(Long id){
		int i=wenchDao.remove(id);
		int j=toolOtherDao.remove(id);
		int k=toolInspectionDao.remove(id);
		return k;
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return toolInspectionDao.batchRemove(ids);
	}

	@Transactional
	@Override
	public int batchSaveOrUpdate(ToolInspectionDO toolInspectionDo, List<WenchDO> wenchDoList, List<ToolOtherDO> toolOtherDoList){
		long toolId=0;
		int h=0;
		Date currentTime=new Date();
		if("".equals(toolInspectionDo.getId())||toolInspectionDo.getId()==null){
			toolInspectionDo.setCreateTime(currentTime);
			toolInspectionDo.setUpdateTime(currentTime);
			h=toolInspectionDao.save(toolInspectionDo);
			if(h>0){
				toolId=toolInspectionDo.getId();
			}
		}else{
			toolId=toolInspectionDo.getId();
			wenchDao.remove(toolId);
			toolOtherDao.remove(toolId);
			toolInspectionDo.setUpdateTime(currentTime);
			h=toolInspectionDao.update(toolInspectionDo);
		}

		List<WenchDO> wenchSaveList=new ArrayList();
		List<WenchDO> wenchUpdateList=new ArrayList();

		if(wenchDoList!=null&&wenchDoList.size()>0){
			for(int i=0;i<wenchDoList.size();i++){
				if("".equals(wenchDoList.get(i).getToolId())||wenchDoList.get(i).getToolId()==null){

					WenchDO sWench= wenchDoList.get(i);
					sWench.setToolId(toolId);
					sWench.setCreateTime(currentTime);
					sWench.setUpdateTime(currentTime);
					wenchSaveList.add(sWench);

				}else{

					WenchDO uWench= wenchDoList.get(i);
					uWench.setId(null);
					uWench.setUpdateTime(currentTime);
					wenchSaveList.add(uWench);
				}

			}

		}
		int j=0;
		int k=0;
		if(wenchSaveList.size()>0&&wenchSaveList!=null){j=wenchDao.batchSave(wenchSaveList);}
		//if(wenchUpdateList.size()>0&&wenchUpdateList!=null){k=wenchDao.batchUpdate(wenchUpdateList);}

		List<ToolOtherDO> otherToolSaveList=new ArrayList();
		List<ToolOtherDO> otherToolUpdateList=new ArrayList();

		if(toolOtherDoList!=null&&toolOtherDoList.size()>0){
			for(int i=0;i<toolOtherDoList.size();i++){
				if("".equals(toolOtherDoList.get(i).getToolId())||toolOtherDoList.get(i).getToolId()==null){
					ToolOtherDO sOtherTool= toolOtherDoList.get(i);
					sOtherTool.setToolId(toolId);
					sOtherTool.setCreateTime(currentTime);
					sOtherTool.setUpdateTime(currentTime);
					otherToolSaveList.add(sOtherTool);

				}else{
					ToolOtherDO uOtherTool= toolOtherDoList.get(i);
					uOtherTool.setId(null);
					uOtherTool.setUpdateTime(currentTime);
					otherToolSaveList.add(uOtherTool);
				}

			}

		}
		int l=0;
		int m=0;
		if(otherToolSaveList.size()>0&&otherToolSaveList!=null){l=toolOtherDao.batchSave(otherToolSaveList);}
		///if(otherToolUpdateList.size()>0&&otherToolUpdateList!=null){m=toolOtherDao.batchUpdate(otherToolUpdateList);}

		return h+j+k+l+m;
	}
}
