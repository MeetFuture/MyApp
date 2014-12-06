package com.tangqiang.backends.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 系统Spring IOC容器管理
 * 
 * @author tom
 * @since 2013-8-3 上午1:16:18
 * @project app-backends-service
 * @package com.tangqiang.backends.app
 */
public class AppContext {
	private static ClassPathXmlApplicationContext instance;

	/** 后台bean配置 */
	private static final String APP_BACKENDS_CONTEXT = "applicationContext.xml";

	/**
	 * 获取bean 容器
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ClassPathXmlApplicationContext getInstance() {
		if (instance == null) {
			try {
				instance = new ClassPathXmlApplicationContext(APP_BACKENDS_CONTEXT);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

}
