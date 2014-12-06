package com.tangqiang.db.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tangqiang.db.app.orm.AppSystemParams;

/**
 * 系统参数信息实体表
 * 
 * @author 汤强
 * @since 2013-8-1 下午8:45:04
 * @project app-db
 * @package com.tangqiang.db.app.entity
 */
@Entity
@Table(name = "APP_SYSTEM_PARAMS")
public class TAppSystemParams extends AppSystemParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7116459676118588565L;

}
