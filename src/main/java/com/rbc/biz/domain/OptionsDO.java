package com.rbc.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 任务项目表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-21 10:57:36
 */
public class OptionsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//下拉选项名称
	private String optionName;
	//选项值
	private String optionValue;
	//下拉选项类型
	private String optionType;
	//是否有效
	private Integer isvaliable;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：下拉选项名称
	 */
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	/**
	 * 获取：下拉选项名称
	 */
	public String getOptionName() {
		return optionName;
	}
	/**
	 * 设置：选项值
	 */
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	/**
	 * 获取：选项值
	 */
	public String getOptionValue() {
		return optionValue;
	}
	/**
	 * 设置：下拉选项类型
	 */
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	/**
	 * 获取：下拉选项类型
	 */
	public String getOptionType() {
		return optionType;
	}
	/**
	 * 设置：是否有效
	 */
	public void setIsvaliable(Integer isvaliable) {
		this.isvaliable = isvaliable;
	}
	/**
	 * 获取：是否有效
	 */
	public Integer getIsvaliable() {
		return isvaliable;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
