package com.rbc.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 试验工具检视信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-05 18:07:04
 */
public class TestToolDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//风表误差±10范围
	private Integer windErrorValue;
	//风表是否超期
	private Integer isWindExced;
	//温度
	private Integer temperature;
	//湿度
	private Integer humidity;
	//705试验台1机能
	private String fu705test1;
	//705试验台2机能
	private String fu705test2;
	//705试验台3机能
	private String fu705test3;
	//F8试验台1机能
	private String fuf8test1;
	//F8试验台2机能
	private String fuf8test2;
	//705试验台1故障
	private String fa705test1;
	//705试验台2故障
	private String fa705test2;
	//705试验台3故障
	private String fa705test3;
	//F8试验台1故障
	private String faf8test1;
	//F8试验台2故障
	private String faf8test2;
	//705试验台1备注
	private String mk705test1;
	//705试验台2备注
	private String mk705test2;
	//705试验台3备注
	private String mk705test3;
	//试验者工号
	private String testerNo;
	//试验者名称
	private String testerName;
	//工长工号
	private String gangmasterNo;
	//工长名称
	private String gangmasterName;
	//工长审核意见：0不同意 1同意
	private Integer gangmasterAudit;
	//质检员工号
	private String inspectorNo;
	//质检员名称
	private String inspectorName;
	//质检员审核意见
	private Integer inspectorAudit;
	//开工意见
	private String permissionsOpinion;
	//返回意见
	private String returnOpinion;
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
	 * 设置：风表误差±10范围
	 */
	public void setWindErrorValue(Integer windErrorValue) {
		this.windErrorValue = windErrorValue;
	}
	/**
	 * 获取：风表误差±10范围
	 */
	public Integer getWindErrorValue() {
		return windErrorValue;
	}
	/**
	 * 设置：风表是否超期
	 */
	public void setIsWindExced(Integer isWindExced) {
		this.isWindExced = isWindExced;
	}
	/**
	 * 获取：风表是否超期
	 */
	public Integer getIsWindExced() {
		return isWindExced;
	}
	/**
	 * 设置：温度
	 */
	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}
	/**
	 * 获取：温度
	 */
	public Integer getTemperature() {
		return temperature;
	}
	/**
	 * 设置：湿度
	 */
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}
	/**
	 * 获取：湿度
	 */
	public Integer getHumidity() {
		return humidity;
	}
	/**
	 * 设置：705试验台1机能
	 */
	public void setFu705test1(String fu705test1) {
		this.fu705test1 = fu705test1;
	}
	/**
	 * 获取：705试验台1机能
	 */
	public String getFu705test1() {
		return fu705test1;
	}
	/**
	 * 设置：705试验台2机能
	 */
	public void setFu705test2(String fu705test2) {
		this.fu705test2 = fu705test2;
	}
	/**
	 * 获取：705试验台2机能
	 */
	public String getFu705test2() {
		return fu705test2;
	}
	/**
	 * 设置：705试验台3机能
	 */
	public void setFu705test3(String fu705test3) {
		this.fu705test3 = fu705test3;
	}
	/**
	 * 获取：705试验台3机能
	 */
	public String getFu705test3() {
		return fu705test3;
	}
	/**
	 * 设置：F8试验台1机能
	 */
	public void setFuf8test1(String fuf8test1) {
		this.fuf8test1 = fuf8test1;
	}
	/**
	 * 获取：F8试验台1机能
	 */
	public String getFuf8test1() {
		return fuf8test1;
	}
	/**
	 * 设置：F8试验台2机能
	 */
	public void setFuf8test2(String fuf8test2) {
		this.fuf8test2 = fuf8test2;
	}
	/**
	 * 获取：F8试验台2机能
	 */
	public String getFuf8test2() {
		return fuf8test2;
	}
	/**
	 * 设置：705试验台1故障
	 */
	public void setFa705test1(String fa705test1) {
		this.fa705test1 = fa705test1;
	}
	/**
	 * 获取：705试验台1故障
	 */
	public String getFa705test1() {
		return fa705test1;
	}
	/**
	 * 设置：705试验台2故障
	 */
	public void setFa705test2(String fa705test2) {
		this.fa705test2 = fa705test2;
	}
	/**
	 * 获取：705试验台2故障
	 */
	public String getFa705test2() {
		return fa705test2;
	}
	/**
	 * 设置：705试验台3故障
	 */
	public void setFa705test3(String fa705test3) {
		this.fa705test3 = fa705test3;
	}
	/**
	 * 获取：705试验台3故障
	 */
	public String getFa705test3() {
		return fa705test3;
	}
	/**
	 * 设置：F8试验台1故障
	 */
	public void setFaf8test1(String faf8test1) {
		this.faf8test1 = faf8test1;
	}
	/**
	 * 获取：F8试验台1故障
	 */
	public String getFaf8test1() {
		return faf8test1;
	}
	/**
	 * 设置：F8试验台2故障
	 */
	public void setFaf8test2(String faf8test2) {
		this.faf8test2 = faf8test2;
	}
	/**
	 * 获取：F8试验台2故障
	 */
	public String getFaf8test2() {
		return faf8test2;
	}
	/**
	 * 设置：705试验台1备注
	 */
	public void setMk705test1(String mk705test1) {
		this.mk705test1 = mk705test1;
	}
	/**
	 * 获取：705试验台1备注
	 */
	public String getMk705test1() {
		return mk705test1;
	}
	/**
	 * 设置：705试验台2备注
	 */
	public void setMk705test2(String mk705test2) {
		this.mk705test2 = mk705test2;
	}
	/**
	 * 获取：705试验台2备注
	 */
	public String getMk705test2() {
		return mk705test2;
	}
	/**
	 * 设置：705试验台3备注
	 */
	public void setMk705test3(String mk705test3) {
		this.mk705test3 = mk705test3;
	}
	/**
	 * 获取：705试验台3备注
	 */
	public String getMk705test3() {
		return mk705test3;
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
	 * 设置：试验者名称
	 */
	public void setTesterName(String testerName) {
		this.testerName = testerName;
	}
	/**
	 * 获取：试验者名称
	 */
	public String getTesterName() {
		return testerName;
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
	 * 设置：工长审核意见：0不同意 1同意
	 */
	public void setGangmasterAudit(Integer gangmasterAudit) {
		this.gangmasterAudit = gangmasterAudit;
	}
	/**
	 * 获取：工长审核意见：0不同意 1同意
	 */
	public Integer getGangmasterAudit() {
		return gangmasterAudit;
	}
	/**
	 * 设置：质检员工号
	 */
	public void setInspectorNo(String inspectorNo) {
		this.inspectorNo = inspectorNo;
	}
	/**
	 * 获取：质检员工号
	 */
	public String getInspectorNo() {
		return inspectorNo;
	}
	/**
	 * 设置：质检员名称
	 */
	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}
	/**
	 * 获取：质检员名称
	 */
	public String getInspectorName() {
		return inspectorName;
	}
	/**
	 * 设置：质检员审核意见
	 */
	public void setInspectorAudit(Integer inspectorAudit) {
		this.inspectorAudit = inspectorAudit;
	}
	/**
	 * 获取：质检员审核意见
	 */
	public Integer getInspectorAudit() {
		return inspectorAudit;
	}
	/**
	 * 设置：开工意见
	 */
	public void setPermissionsOpinion(String permissionsOpinion) {
		this.permissionsOpinion = permissionsOpinion;
	}
	/**
	 * 获取：开工意见
	 */
	public String getPermissionsOpinion() {
		return permissionsOpinion;
	}
	/**
	 * 设置：返回意见
	 */
	public void setReturnOpinion(String returnOpinion) {
		this.returnOpinion = returnOpinion;
	}
	/**
	 * 获取：返回意见
	 */
	public String getReturnOpinion() {
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
