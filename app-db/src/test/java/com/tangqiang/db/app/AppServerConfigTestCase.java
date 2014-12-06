package com.tangqiang.db.app;

import org.apache.log4j.Logger;

import com.tangqiang.db.app.entity.TAppServerConfig;
import com.tangqiang.db.base.orm.IDao;
import com.tangqiang.db.base.test.HibernateTestCase;

/**
 * 测试服务配置
 *
 * @author tom
 * @since 2013-8-2 上午12:16:20
 * @project app-db
 * @package com.tangqiang.db.app
 */
public class AppServerConfigTestCase extends HibernateTestCase{
	private Logger logger = Logger.getLogger(AppServerConfigTestCase.class);
	private IDao<TAppServerConfig, Long> appServerConfigDao;
	
	public void testAdd() throws Exception {
		logger.info("开始添加!");
		TAppServerConfig t = new TAppServerConfig();
		t.setServerName("某某服务");
		
		appServerConfigDao.save(t);
		setComplete();
		logger.info("添加完成!");
	}

	public IDao<TAppServerConfig, Long> getAppServerConfigDao() {
		return appServerConfigDao;
	}

	public void setAppServerConfigDao(IDao<TAppServerConfig, Long> appServerConfigDao) {
		this.appServerConfigDao = appServerConfigDao;
	}
	
	

}
