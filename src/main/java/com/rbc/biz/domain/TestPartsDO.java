package com.rbc.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 试验部件信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-08-10 01:38:55
 */
public class TestPartsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//测试部件编号
	private String testPartNo;
	//组装者工号
	private String assemblerNo;
	//组装者名称
	private String assemblerName;
	//组装日期
	private Date assembleDate;
	//是否合格 0不合格 1合格
	private Integer isPass;
	//故障描述
	private String faultDescription;
	//返工情况
	private String retanSit;
	//创建日期
	private Date createTime;
	//更新日期
	private Date updateTime;
	//试验部件类型 104紧急阀  104主阀 F8辅助阀  F8主阀
	private String testPartType;
	//试验者工号
	private String testerNo;
	//试验名称
	private String testerName;

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
	 * 设置：测试部件编号
	 */
	public void setTestPartNo(String testPartNo) {
		this.testPartNo = testPartNo;
	}
	/**
	 * 获取：测试部件编号
	 */
	public String getTestPartNo() {
		return testPartNo;
	}
	/**
	 * 设置：组装者工号
	 */
	public void setAssemblerNo(String assemblerNo) {
		this.assemblerNo = assemblerNo;
	}
	/**
	 * 获取：组装者工号
	 */
	public String getAssemblerNo() {
		return assemblerNo;
	}
	/**
	 * 设置：组装者名称
	 */
	public void setAssemblerName(String assemblerName) {
		this.assemblerName = assemblerName;
	}
	/**
	 * 获取：组装者名称
	 */
	public String getAssemblerName() {
		return assemblerName;
	}
	/**
	 * 设置：组装日期
	 */
	public void setAssembleDate(Date assembleDate) {
		this.assembleDate = assembleDate;
	}
	/**
	 * 获取：组装日期
	 */
	public Date getAssembleDate() {
		return assembleDate;
	}
	/**
	 * 设置：是否合格 0不合格 1合格
	 */
	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}
	/**
	 * 获取：是否合格 0不合格 1合格
	 */
	public Integer getIsPass() {
		return isPass;
	}
	/**
	 * 设置：故障描述
	 */
	public void setFaultDescription(String faultDescription) {
		this.faultDescription = faultDescription;
	}
	/**
	 * 获取：故障描述
	 */
	public String getFaultDescription() {
		return faultDescription;
	}
	/**
	 * 设置：返工情况
	 */
	public void setRetanSit(String retanSit) {
		this.retanSit = retanSit;
	}
	/**
	 * 获取：返工情况
	 */
	public String getRetanSit() {
		return retanSit;
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
	 * 设置：试验部件类型 104紧急阀  104主阀 F8辅助阀  F8主阀
	 */
	public void setTestPartType(String testPartType) {
		this.testPartType = testPartType;
	}
	/**
	 * 获取：试验部件类型 104紧急阀  104主阀 F8辅助阀  F8主阀
	 */
	public String getTestPartType() {
		return testPartType;
	}
	/**
	 * 设置：试验者工号
	 */
	public void setTesterNo(String testerNo) {
		this.testerNo = testerNo;
	}
	/**
	 * 获取：试验者工号
	 */
	public String getTesterNo() {
		return testerNo;
	}
	/**
	 * 设置：试验名称
	 */
	public void setTesterName(String testerName) {
		this.testerName = testerName;
	}
	/**
	 * 获取：试验名称
	 */
	public String getTesterName() {
		return testerName;
	}
}
