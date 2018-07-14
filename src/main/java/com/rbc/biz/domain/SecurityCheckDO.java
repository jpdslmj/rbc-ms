package com.rbc.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 安全质量检查表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-10 16:57:17
 */
public class SecurityCheckDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//质检项目名
	private String securityProject;
	//安全质量状况描述
	private String description;
	//质检状态
	private String status;
	//工长工号
	private String gangmasterNo;
	//工长名称
	private String gangmasterName;
	//质检日期
	private String checkTime;
	//备注
	private String remark;
	//创建日期
	private Date createTime;
	//更新日期
	private Date updateTime;
	//日志表主键
	private Long logId;

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
	 * 设置：质检项目名
	 */
	public void setSecurityProject(String securityProject) {
		this.securityProject = securityProject;
	}
	/**
	 * 获取：质检项目名
	 */
	public String getSecurityProject() {
		return securityProject;
	}
	/**
	 * 设置：安全质量状况描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：安全质量状况描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：质检状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：质检状态
	 */
	public String getStatus() {
		return status;
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
	 * 设置：质检日期
	 */
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	/**
	 * 获取：质检日期
	 */
	public String getCheckTime() {
		return checkTime;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
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
	/**
	 * 设置：日志表主键
	 */
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	/**
	 * 获取：日志表主键
	 */
	public Long getLogId() {
		return logId;
	}
}
