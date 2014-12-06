package com.tangqiang.backends;

import org.apache.log4j.Logger;

import com.tangqiang.backends.app.AppContext;
import com.tangqiang.backends.server.IMyServer;

/**
 * App启动主函数
 * 
 * @author 汤强
 * @since 2013-8-2 下午2:55:30
 * @project app-backends-app
 * @package com.tangqiang.backends
 */
public class AppMain {
	private Logger logger = Logger.getLogger(AppMain.class);

	/** app启动的主服务bean名称 */
	private static final String APP_MAIN_SERVER = "MainServer";

	public static void main(String[] args) {
		AppMain m = new AppMain();
		m.start();
	}
	
	/**
	 * 启动程序
	 */
	private void start() {
		logger.info("开始启动app服务!");
		boolean bExist = AppContext.getInstance().containsBean(APP_MAIN_SERVER);
		if (bExist) {
			IMyServer mainServer = (IMyServer) AppContext.getInstance().getBean(APP_MAIN_SERVER);
			mainServer.startServer();
		}
		logger.info("启动app服务完成!");
	}

}
