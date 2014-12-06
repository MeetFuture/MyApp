package com.tangqiang.backends.service;

import java.util.List;

import com.tangqiang.db.app.entity.TAppServerConfig;
import com.tangqiang.db.app.entity.TAppSystemParams;

/**
 * 系统配置查询服务接口
 * 
 * @author tom
 * @since 2013-8-1 下午11:25:56
 * @project app-utils
 * @package com.tangqiang.utils.services
 */
public interface IAppConfigService {

	/**
	 * 获取所有的服务信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TAppServerConfig> allAppServer() throws Exception;
	
	
	/**
	 * 获取所有的系统参数信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TAppSystemParams> allAppSystemParams() throws Exception;

}
