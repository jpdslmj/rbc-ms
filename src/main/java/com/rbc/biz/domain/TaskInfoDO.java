package com.rbc.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 任务信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
public class TaskInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//发布表主键
	private String distributionId;
	//序列号
	private String sequenceNo;
	//检修项目
	private String fixTask;
	//计划数量
	private Long planNum;
	//实际数量
	private Long realNum;
	//工作者工号
	private String fixWorkerNo;
	//工作者名称
	private String fixWorkerName;
	//工长工号
	private String gangmasterNo;
	//工长名称
	private String gangmasterName;
	//审核意见
	private Integer auditOpinion;
	//审核备注
	private String auditRemark;
	//创建日期
	private Date createTime;
	//更新日期
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：发布表主键
	 */
	public void setDistributionId(String distributionId) {
		this.distributionId = distributionId;
	}
	/**
	 * 获取：发布表主键
	 */
	public String getDistributionId() {
		return distributionId;
	}
	/**
	 * 设置：序列号
	 */
	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	/**
	 * 获取：序列号
	 */
	public String getSequenceNo() {
		return sequenceNo;
	}
	/**
	 * 设置：检修项目
	 */
	public void setFixTask(String fixTask) {
		this.fixTask = fixTask;
	}
	/**
	 * 获取：检修项目
	 */
	public String getFixTask() {
		return fixTask;
	}
	/**
	 * 设置：计划数量
	 */
	public void setPlanNum(Long planNum) {
		this.planNum = planNum;
	}
	/**
	 * 获取：计划数量
	 */
	public Long getPlanNum() {
		return planNum;
	}
	/**
	 * 设置：实际数量
	 */
	public void setRealNum(Long realNum) {
		this.realNum = realNum;
	}
	/**
	 * 获取：实际数量
	 */
	public Long getRealNum() {
		return realNum;
	}
	/**
	 * 设置：工作者工号
	 */
	public void setFixWorkerNo(String fixWorkerNo) {
		this.fixWorkerNo = fixWorkerNo;
	}
	/**
	 * 获取：工作者工号
	 */
	public String getFixWorkerNo() {
		return fixWorkerNo;
	}
	/**
	 * 设置：工作者名称
	 */
	public void setFixWorkerName(String fixWorkerName) {
		this.fixWorkerName = fixWorkerName;
	}
	/**
	 * 获取：工作者名称
	 */
	public String getFixWorkerName() {
		return fixWorkerName;
	}
	/**
	 * 设置：工长工号
	 */
	public void setGangmasterNo(String gangmasterNo) {
		this.gangmasterNo = gangmasterNo;
	}
	/**
	 * 获取：工长工号
	 */
	public String getGangmasterNo() {
		return gangmasterNo;
	}
	/**
	 * 设置：工长名称
	 */
	public void setGangmasterName(String gangmasterName) {
		this.gangmasterName = gangmasterName;
	}
	/**
	 * 获取：工长名称
	 */
	public String getGangmasterName() {
		return gangmasterName;
	}
	/**
	 * 设置：审核意见
	 */
	public void setAuditOpinion(Integer auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	/**
	 * 获取：审核意见
	 */
	public Integer getAuditOpinion() {
		return auditOpinion;
	}
	/**
	 * 设置：审核备注
	 */
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
	/**
	 * 获取：审核备注
	 */
	public String getAuditRemark() {
		return auditRemark;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新日期
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新日期
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
