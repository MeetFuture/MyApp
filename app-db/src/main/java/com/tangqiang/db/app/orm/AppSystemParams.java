package com.tangqiang.db.app.orm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.tangqiang.db.base.entity.BaseORMEntity;

/**
 * 系统配置参数表
 * 
 * @author 汤强
 * @since 2013-8-1 下午8:18:04
 * @project app-db
 * @package com.tangqiang.db.app.orm
 */
@MappedSuperclass
public class AppSystemParams extends BaseORMEntity {

	/** id */
	protected int paramId;
	/** 描述 */
	protected String paramName;
	/** 值 */
	protected String paramValue;
	
	@Basic
	@Column(name = "PARAMID",unique=true,nullable=false)
	public int getParamId() {
		return paramId;
	}
	public void setParamId(int paramId) {
		this.paramId = paramId;
	}
	
	@Basic
	@Column(name = "PARAMNAME",length=32)
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	@Basic
	@Column(name = "PARAMVALUE",length=100)
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	


}
