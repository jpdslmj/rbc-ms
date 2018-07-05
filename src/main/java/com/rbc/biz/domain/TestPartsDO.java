package com.rbc.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 试验部件信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
public class TestPartsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//紧急阀编号
	private String popValue;
	//紧急阀编号
	private String sequenceNo;
	//测试部件编号
	private String testPartNo;
	//组装者工号
	private String assemblerNo;
	//组装者名称
	private String assemblerName;
	//组装日期
	private String assembleDate;
	//是否合格
	private String isPass;
	//故障描述
	private String faultDescription;
	//返工情况
	private String retanSit;
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
	 * 设置：紧急阀编号
	 */
	public void setPopValue(String popValue) {
		this.popValue = popValue;
	}
	/**
	 * 获取：紧急阀编号
	 */
	public String getPopValue() {
		return popValue;
	}
	/**
	 * 设置：紧急阀编号
	 */
	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	/**
	 * 获取：紧急阀编号
	 */
	public String getSequenceNo() {
		return sequenceNo;
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
	public void setAssembleDate(String assembleDate) {
		this.assembleDate = assembleDate;
	}
	/**
	 * 获取：组装日期
	 */
	public String getAssembleDate() {
		return assembleDate;
	}
	/**
	 * 设置：是否合格
	 */
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	/**
	 * 获取：是否合格
	 */
	public String getIsPass() {
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
}
