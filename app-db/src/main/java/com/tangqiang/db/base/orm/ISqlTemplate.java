package com.tangqiang.db.base.orm;

import java.util.Map;

/**
 * sql组装接口(根据不同的数据库操作方式,选择适用的sql组装方法)
 * 
 * @author 汤强
 * 
 */
public interface ISqlTemplate {

	/**
	 * 组装sql
	 * 
	 * @param params
	 * @return
	 */
	public String updateSetString(Map<String, Object> params);
}
