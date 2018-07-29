package com.rbc.common.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 文件上传
 * 
 * @author lmj
 * @email 359819418@qq.com
 * @date 2018-07-26 17:42:17
 */
public class FileDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//文件类型
	private Integer type;
	//URL地址
	private String url;
	//
	private String fileRealName;
	//
	private String partType;
	//
	private Long partId;
	//创建时间
	private Date createDate;

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
	 * 设置：文件类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：文件类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：URL地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：URL地址
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：
	 */
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	/**
	 * 获取：
	 */
	public String getFileRealName() {
		return fileRealName;
	}
	/**
	 * 设置：
	 */
	public void setPartType(String partType) {
		this.partType = partType;
	}
	/**
	 * 获取：
	 */
	public String getPartType() {
		return partType;
	}
	/**
	 * 设置：
	 */
	public void setPartId(Long partId) {
		this.partId = partId;
	}
	/**
	 * 获取：
	 */
	public Long getPartId() {
		return partId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
}
