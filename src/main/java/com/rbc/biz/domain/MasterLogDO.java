package com.rbc.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 工长日志表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
public class MasterLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//天气
	private String weather;
	//班前预想
	private String clsEnvision;
	//班前一题
	private String clsOne;
	//任务完成情况和改进措施
	private String taskDetails;
	//班组记事
	private String clsMark;
	//工长工号
	private String gangmasterNo;
	//工长名称
	private String gangmasterName;
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
	 * 设置：天气
	 */
	public void setWeather(String weather) {
		this.weather = weather;
	}
	/**
	 * 获取：天气
	 */
	public String getWeather() {
		return weather;
	}
	/**
	 * 设置：班前预想
	 */
	public void setClsEnvision(String clsEnvision) {
		this.clsEnvision = clsEnvision;
	}
	/**
	 * 获取：班前预想
	 */
	public String getClsEnvision() {
		return clsEnvision;
	}
	/**
	 * 设置：班前一题
	 */
	public void setClsOne(String clsOne) {
		this.clsOne = clsOne;
	}
	/**
	 * 获取：班前一题
	 */
	public String getClsOne() {
		return clsOne;
	}
	/**
	 * 设置：任务完成情况和改进措施
	 */
	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}
	/**
	 * 获取：任务完成情况和改进措施
	 */
	public String getTaskDetails() {
		return taskDetails;
	}
	/**
	 * 设置：班组记事
	 */
	public void setClsMark(String clsMark) {
		this.clsMark = clsMark;
	}
	/**
	 * 获取：班组记事
	 */
	public String getClsMark() {
		return clsMark;
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
