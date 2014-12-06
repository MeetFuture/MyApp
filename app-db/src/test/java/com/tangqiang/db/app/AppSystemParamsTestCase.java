package com.tangqiang.db.app;

import com.tangqiang.db.app.entity.TAppSystemParams;
import com.tangqiang.db.base.orm.IDao;
import com.tangqiang.db.base.test.HibernateTestCase;

/**
 * 系统参数测试
 *
 * @author tom
 * @since 2013-8-3 上午10:22:01
 * @project app-db
 * @package com.tangqiang.db.app
 */
public class AppSystemParamsTestCase extends HibernateTestCase{
	private IDao<TAppSystemParams, Long> appSystemParamsDao;
	
	
	public void testAdd() throws Exception {
		TAppSystemParams tp = new TAppSystemParams();
		tp.setParamId(0X0001);
		tp.setParamValue("asdasd");
		tp.setParamName("测试");
		appSystemParamsDao.save(tp);
		setComplete();
	}


	public IDao<TAppSystemParams, Long> getAppSystemParamsDao() {
		return appSystemParamsDao;
	}


	public void setAppSystemParamsDao(IDao<TAppSystemParams, Long> appSystemParamsDao) {
		this.appSystemParamsDao = appSystemParamsDao;
	}
	
}
