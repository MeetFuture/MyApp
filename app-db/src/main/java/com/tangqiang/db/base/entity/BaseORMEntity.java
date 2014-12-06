package com.tangqiang.db.base.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * 基本实体
 * 
 * @author 汤强
 * @since 2013-7-30 下午4:18:44
 * @project app-db
 * @package com.tangqiang.db.orm.hibernate
 */
@MappedSuperclass
public abstract class BaseORMEntity extends Entity {
	public static final boolean DELETE_STATUS_YES = true;
	
	/** id */
	protected Long id;
	
	protected Date createTime = new Date();

	protected Date updateTime = createTime;

	protected boolean deleteStatus;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Basic
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Basic
	@Column(name = "DELSTATUS")
	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
	@SuppressWarnings("rawtypes")
	@Transient
	public Class getType() {
		return this.getClass();
	}
}
