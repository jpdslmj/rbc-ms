package com.rbc.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 104紧急阀信息表
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-07 10:22:40
 */
public class PopValve104DO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//104紧急阀编号
	private String popValue;
	//分解者工号
	private String disassembleNo;
	//分解者名称
	private String disassembleName;
	//清洗者工号
	private String cleanerNo;
	//清洗者名称
	private String cleanerName;
	//检修者1工号
	private String fixer1No;
	//检修者1名称
	private String fixer1Name;
	//检修者1备注
	private String fixer1Remark;
	//检修者2工号
	private String fixer2No;
	//检修者2名称
	private String fixer2Name;
	//检修者2备注
	private String fixer2Remark;
	//检修者3工号
	private String fixer3No;
	//检修者3名称
	private String fixer3Name;
	//检修者3备注
	private String fixer3Remark;
	//检修者4工号
	private String fixer4No;
	//检修者4名称
	private String fixer4Name;
	//检修者4备注
	private String fixer4Remark;
	//检修工序
	private Integer fixerProcess;
	//检修工序详情
	private String fixerProcessDetail;
	//组装者工号
	private String assemblerNo;
	//组装者名称
	private String assemblerName;
	//组装者备注
	private String assemblerRemark;
	//组装人字标志
	private String assembleMark;
	//工长工号
	private String gangmasterNo;
	//工长名称
	private String gangmasterName;
	//工长审核意见：1同意 0不同意
	private Integer gangmasterAudit;
	//工长审核备注
	private String gangmasterRemark;
	//工长一字标志
	private Long gangmasterMark;
	//质检员工号
	private String inspectorNo;
	//质检员名称
	private String inspectorName;
	//质检员合字标志
	private Long inspectorMark;
	//质检员审核意见：1同意 0不同意
	private Integer inspectorAudit;
	//质检员审核备注
	private String inspectorRemark;
	//退回意见：1同意 0不同意
	private Integer returnOpinion;
	//退回备注
	private String returnRemark;
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
	 * 设置：104紧急阀编号
	 */
	public void setPopValue(String popValue) {
		this.popValue = popValue;
	}
	/**
	 * 获取：104紧急阀编号
	 */
	public String getPopValue() {
		return popValue;
	}
	/**
	 * 设置：分解者工号
	 */
	public void setDisassembleNo(String disassembleNo) {
		this.disassembleNo = disassembleNo;
	}
	/**
	 * 获取：分解者工号
	 */
	public String getDisassembleNo() {
		return disassembleNo;
	}
	/**
	 * 设置：分解者名称
	 */
	public void setDisassembleName(String disassembleName) {
		this.disassembleName = disassembleName;
	}
	/**
	 * 获取：分解者名称
	 */
	public String getDisassembleName() {
		return disassembleName;
	}
	/**
	 * 设置：清洗者工号
	 */
	public void setCleanerNo(String cleanerNo) {
		this.cleanerNo = cleanerNo;
	}
	/**
	 * 获取：清洗者工号
	 */
	public String getCleanerNo() {
		return cleanerNo;
	}
	/**
	 * 设置：清洗者名称
	 */
	public void setCleanerName(String cleanerName) {
		this.cleanerName = cleanerName;
	}
	/**
	 * 获取：清洗者名称
	 */
	public String getCleanerName() {
		return cleanerName;
	}
	/**
	 * 设置：检修者1工号
	 */
	public void setFixer1No(String fixer1No) {
		this.fixer1No = fixer1No;
	}
	/**
	 * 获取：检修者1工号
	 */
	public String getFixer1No() {
		return fixer1No;
	}
	/**
	 * 设置：检修者1名称
	 */
	public void setFixer1Name(String fixer1Name) {
		this.fixer1Name = fixer1Name;
	}
	/**
	 * 获取：检修者1名称
	 */
	public String getFixer1Name() {
		return fixer1Name;
	}
	/**
	 * 设置：检修者1备注
	 */
	public void setFixer1Remark(String fixer1Remark) {
		this.fixer1Remark = fixer1Remark;
	}
	/**
	 * 获取：检修者1备注
	 */
	public String getFixer1Remark() {
		return fixer1Remark;
	}
	/**
	 * 设置：检修者2工号
	 */
	public void setFixer2No(String fixer2No) {
		this.fixer2No = fixer2No;
	}
	/**
	 * 获取：检修者2工号
	 */
	public String getFixer2No() {
		return fixer2No;
	}
	/**
	 * 设置：检修者2名称
	 */
	public void setFixer2Name(String fixer2Name) {
		this.fixer2Name = fixer2Name;
	}
	/**
	 * 获取：检修者2名称
	 */
	public String getFixer2Name() {
		return fixer2Name;
	}
	/**
	 * 设置：检修者2备注
	 */
	public void setFixer2Remark(String fixer2Remark) {
		this.fixer2Remark = fixer2Remark;
	}
	/**
	 * 获取：检修者2备注
	 */
	public String getFixer2Remark() {
		return fixer2Remark;
	}
	/**
	 * 设置：检修者3工号
	 */
	public void setFixer3No(String fixer3No) {
		this.fixer3No = fixer3No;
	}
	/**
	 * 获取：检修者3工号
	 */
	public String getFixer3No() {
		return fixer3No;
	}
	/**
	 * 设置：检修者3名称
	 */
	public void setFixer3Name(String fixer3Name) {
		this.fixer3Name = fixer3Name;
	}
	/**
	 * 获取：检修者3名称
	 */
	public String getFixer3Name() {
		return fixer3Name;
	}
	/**
	 * 设置：检修者3备注
	 */
	public void setFixer3Remark(String fixer3Remark) {
		this.fixer3Remark = fixer3Remark;
	}
	/**
	 * 获取：检修者3备注
	 */
	public String getFixer3Remark() {
		return fixer3Remark;
	}
	/**
	 * 设置：检修者4工号
	 */
	public void setFixer4No(String fixer4No) {
		this.fixer4No = fixer4No;
	}
	/**
	 * 获取：检修者4工号
	 */
	public String getFixer4No() {
		return fixer4No;
	}
	/**
	 * 设置：检修者4名称
	 */
	public void setFixer4Name(String fixer4Name) {
		this.fixer4Name = fixer4Name;
	}
	/**
	 * 获取：检修者4名称
	 */
	public String getFixer4Name() {
		return fixer4Name;
	}
	/**
	 * 设置：检修者4备注
	 */
	public void setFixer4Remark(String fixer4Remark) {
		this.fixer4Remark = fixer4Remark;
	}
	/**
	 * 获取：检修者4备注
	 */
	public String getFixer4Remark() {
		return fixer4Remark;
	}
	/**
	 * 设置：检修工序
	 */
	public void setFixerProcess(Integer fixerProcess) {
		this.fixerProcess = fixerProcess;
	}
	/**
	 * 获取：检修工序
	 */
	public Integer getFixerProcess() {
		return fixerProcess;
	}
	/**
	 * 设置：检修工序详情
	 */
	public void setFixerProcessDetail(String fixerProcessDetail) {
		this.fixerProcessDetail = fixerProcessDetail;
	}
	/**
	 * 获取：检修工序详情
	 */
	public String getFixerProcessDetail() {
		return fixerProcessDetail;
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
	 * 设置：组装者备注
	 */
	public void setAssemblerRemark(String assemblerRemark) {
		this.assemblerRemark = assemblerRemark;
	}
	/**
	 * 获取：组装者备注
	 */
	public String getAssemblerRemark() {
		return assemblerRemark;
	}
	/**
	 * 设置：组装人字标志
	 */
	public void setAssembleMark(String assembleMark) {
		this.assembleMark = assembleMark;
	}
	/**
	 * 获取：组装人字标志
	 */
	public String getAssembleMark() {
		return assembleMark;
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
	 * 设置：工长审核备注
	 */
	public void setGangmasterRemark(String gangmasterRemark) {
		this.gangmasterRemark = gangmasterRemark;
	}
	/**
	 * 获取：工长审核备注
	 */
	public String getGangmasterRemark() {
		return gangmasterRemark;
	}
	/**
	 * 设置：工长一字标志
	 */
	public void setGangmasterMark(Long gangmasterMark) {
		this.gangmasterMark = gangmasterMark;
	}
	/**
	 * 获取：工长一字标志
	 */
	public Long getGangmasterMark() {
		return gangmasterMark;
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
	 * 设置：质检员合字标志
	 */
	public void setInspectorMark(Long inspectorMark) {
		this.inspectorMark = inspectorMark;
	}
	/**
	 * 获取：质检员合字标志
	 */
	public Long getInspectorMark() {
		return inspectorMark;
	}
	/**
	 * 设置：质检员审核意见：1同意 0不同意
	 */
	public void setInspectorAudit(Integer inspectorAudit) {
		this.inspectorAudit = inspectorAudit;
	}
	/**
	 * 获取：质检员审核意见：1同意 0不同意
	 */
	public Integer getInspectorAudit() {
		return inspectorAudit;
	}
	/**
	 * 设置：质检员审核备注
	 */
	public void setInspectorRemark(String inspectorRemark) {
		this.inspectorRemark = inspectorRemark;
	}
	/**
	 * 获取：质检员审核备注
	 */
	public String getInspectorRemark() {
		return inspectorRemark;
	}
	/**
	 * 设置：退回意见：1同意 0不同意
	 */
	public void setReturnOpinion(Integer returnOpinion) {
		this.returnOpinion = returnOpinion;
	}
	/**
	 * 获取：退回意见：1同意 0不同意
	 */
	public Integer getReturnOpinion() {
		return returnOpinion;
	}
	/**
	 * 设置：退回备注
	 */
	public void setReturnRemark(String returnRemark) {
		this.returnRemark = returnRemark;
	}
	/**
	 * 获取：退回备注
	 */
	public String getReturnRemark() {
		return returnRemark;
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
