package com.rbc.biz.domain;

import com.rbc.activiti.domain.TaskDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 工具检视信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:05
 */
public class ToolInspectionDO extends TaskDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//白光照度测量
	private Double luminanceMeasure;
	//温度
	private Double temperature;
	//湿度
	private Double humidity;
	//力矩扳手：1全部合格，2部分合格，3全部不合格
	private Long wench;
	//样板
	private Long templet;
	//通针
	private Long nozzleCleaner;
	//硅油
	private Long siliconeOil;
	//硅脂
	private Long siliconeGrease;
	//其他工具
	private Long otherTool;
	//工作者工号
	private String fixWorkerNo;
	//工作者名称
	private String fixWorkerName;
	//工长工号
	private String gangmasterNo;
	//工长名称
	private String gangmasterName;
	//工长审核意见：1同意 0不同意
	private Integer gangmasterAudit;
	//开工结论
	private String permissionsOpinion;
	//退回意见
	private Integer returnOpinion;
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
	 * 设置：白光照度测量
	 */
	public void setLuminanceMeasure(Double luminanceMeasure) {
		this.luminanceMeasure = luminanceMeasure;
	}
	/**
	 * 获取：白光照度测量
	 */
	public Double getLuminanceMeasure() {
		return luminanceMeasure;
	}
	/**
	 * 设置：温度
	 */
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	/**
	 * 获取：温度
	 */
	public Double getTemperature() {
		return temperature;
	}
	/**
	 * 设置：湿度
	 */
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	/**
	 * 获取：湿度
	 */
	public Double getHumidity() {
		return humidity;
	}
	/**
	 * 设置：力矩扳手：1全部合格，2部分合格，3全部不合格
	 */
	public void setWench(Long wench) {
		this.wench = wench;
	}
	/**
	 * 获取：力矩扳手：1全部合格，2部分合格，3全部不合格
	 */
	public Long getWench() {
		return wench;
	}
	/**
	 * 设置：样板
	 */
	public void setTemplet(Long templet) {
		this.templet = templet;
	}
	/**
	 * 获取：样板
	 */
	public Long getTemplet() {
		return templet;
	}
	/**
	 * 设置：通针
	 */
	public void setNozzleCleaner(Long nozzleCleaner) {
		this.nozzleCleaner = nozzleCleaner;
	}
	/**
	 * 获取：通针
	 */
	public Long getNozzleCleaner() {
		return nozzleCleaner;
	}
	/**
	 * 设置：硅油
	 */
	public void setSiliconeOil(Long siliconeOil) {
		this.siliconeOil = siliconeOil;
	}
	/**
	 * 获取：硅油
	 */
	public Long getSiliconeOil() {
		return siliconeOil;
	}
	/**
	 * 设置：硅脂
	 */
	public void setSiliconeGrease(Long siliconeGrease) {
		this.siliconeGrease = siliconeGrease;
	}
	/**
	 * 获取：硅脂
	 */
	public Long getSiliconeGrease() {
		return siliconeGrease;
	}
	/**
	 * 设置：其他工具
	 */
	public void setOtherTool(Long otherTool) {
		this.otherTool = otherTool;
	}
	/**
	 * 获取：其他工具
	 */
	public Long getOtherTool() {
		return otherTool;
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
	 * 设置：工长审核意见：1同意 0不同意
	 */
	public void setGangmasterAudit(Integer gangmasterAudit) {
		this.gangmasterAudit = gangmasterAudit;
	}
	/**
	 * 获取：工长审核意见：1同意 0不同意
	 */
	public Integer getGangmasterAudit() {
		return gangmasterAudit;
	}
	/**
	 * 设置：开工结论
	 */
	public void setPermissionsOpinion(String permissionsOpinion) {
		this.permissionsOpinion = permissionsOpinion;
	}
	/**
	 * 获取：开工结论
	 */
	public String getPermissionsOpinion() {
		return permissionsOpinion;
	}
	/**
	 * 设置：退回意见
	 */
	public void setReturnOpinion(Integer returnOpinion) {
		this.returnOpinion = returnOpinion;
	}
	/**
	 * 获取：退回意见
	 */
	public Integer getReturnOpinion() {
		return returnOpinion;
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
