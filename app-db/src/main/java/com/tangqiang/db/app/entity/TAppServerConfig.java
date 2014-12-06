package com.tangqiang.db.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tangqiang.db.app.orm.AppServerConfig;

/**
 * 系统服务配置表实体
 *
 * @author 汤强
 * @since 2013-8-1 下午8:28:10
 * @project app-db
 * @package com.tangqiang.db.app.entity
 */
@Entity
@Table(name = "APP_SERVER_CONFIG")
public class TAppServerConfig extends AppServerConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7528382579706799012L;

}
