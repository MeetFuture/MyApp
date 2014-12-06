package com.tangqiang.db.app.orm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.tangqiang.db.base.entity.BaseORMEntity;

/**
 * 系统服务配置表
 * 
 * @author 汤强
 * @since 2013-8-1 下午8:18:04
 * @project app-db
 * @package com.tangqiang.db.app.orm
 */
@MappedSuperclass
public class AppServerConfig extends BaseORMEntity {

	/** 服务中文名称 */
	protected String title;
	/** 服务英文名称 */
	protected String serverName;
	/** 服务bean名称 */
	protected String serverBean;
	/** 是否启动 */
	protected boolean enable;

	/** 备用 */
	protected String spareStr;

	@Basic
	@Column(name = "TITLE", length = 20)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Basic
	@Column(name = "SERVERNAME", length = 20)
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@Basic
	@Column(name = "SERVERBEAN", length = 20)
	public String getServerBean() {
		return serverBean;
	}

	public void setServerBean(String serverBean) {
		this.serverBean = serverBean;
	}

	@Basic
	@Column(name = "ENABLE")
	public boolean isEnable() {
		return enable;
	}

	

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Basic
	@Column(name = "SPARESTR", length = 20)
	public String getSpareStr() {
		return spareStr;
	}

	public void setSpareStr(String spareStr) {
		this.spareStr = spareStr;
	}

}
