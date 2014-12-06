package com.tangqiang.backends.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.tangqiang.backends.service.IAppConfigService;
import com.tangqiang.db.app.entity.TAppServerConfig;
import com.tangqiang.db.app.entity.TAppSystemParams;
import com.tangqiang.db.base.orm.IDao;

/**
 * 系统服务配置查询服务实现
 * 
 * @author 汤强
 * @since 2013-8-2 上午11:08:06
 * @project app-utils
 * @package com.tangqiang.utils.services.impl
 */
public class AppConfigServiceImpl implements IAppConfigService {
	private Logger logger = Logger.getLogger(AppConfigServiceImpl.class);

	private IDao<TAppServerConfig, Long> appServerConfigDao;
	private IDao<TAppSystemParams, Long> appSystemParamsDao;

	public List<TAppServerConfig> allAppServer() throws Exception {
		logger.info("开始查询所有的系统服务信息!");
		return appServerConfigDao.all(null);
	}

	public List<TAppSystemParams> allAppSystemParams() throws Exception {
		logger.info("开始查询所有的系统参数信息!");
		return appSystemParamsDao.all(null);
	}

	public IDao<TAppServerConfig, Long> getAppServerConfigDao() {
		return appServerConfigDao;
	}

	public void setAppServerConfigDao(IDao<TAppServerConfig, Long> appServerConfigDao) {
		this.appServerConfigDao = appServerConfigDao;
	}

	public IDao<TAppSystemParams, Long> getAppSystemParamsDao() {
		return appSystemParamsDao;
	}

	public void setAppSystemParamsDao(IDao<TAppSystemParams, Long> appSystemParamsDao) {
		this.appSystemParamsDao = appSystemParamsDao;
	}

}
