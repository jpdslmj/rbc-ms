package com.rbc.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 问题扳手信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-18 00:12:00
 */

public class WenchDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//工具id
	private Long toolId;
	//扳手编号
	private String wenchNo;
	//处置情况
	private String dealSit;
	//备注
	private String remark;
	//创建日期
	private Date createTime;
	//更新日期
	private Date updateTime;
	//
	private Long num;

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
	 * 设置：工具id
	 */
	public void setToolId(Long toolId) {
		this.toolId = toolId;
	}
	/**
	 * 获取：工具id
	 */
	public Long getToolId() {
		return toolId;
	}
	/**
	 * 设置：扳手编号
	 */
	public void setWenchNo(String wenchNo) {
		this.wenchNo = wenchNo;
	}
	/**
	 * 获取：扳手编号
	 */
	public String getWenchNo() {
		return wenchNo;
	}
	/**
	 * 设置：处置情况
	 */
	public void setDealSit(String dealSit) {
		this.dealSit = dealSit;
	}
	/**
	 * 获取：处置情况
	 */
	public String getDealSit() {
		return dealSit;
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
	 * 设置：
	 */
	public void setNum(Long num) {
		this.num = num;
	}
	/**
	 * 获取：
	 */
	public Long getNum() {
		return num;
	}
}
