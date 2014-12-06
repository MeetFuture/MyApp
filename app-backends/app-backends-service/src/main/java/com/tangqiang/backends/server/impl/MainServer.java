package com.tangqiang.backends.server.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.tangqiang.backends.app.AppContext;
import com.tangqiang.backends.server.IMyServer;
import com.tangqiang.backends.service.IAppConfigService;
import com.tangqiang.db.app.entity.TAppServerConfig;


/**
 * 后台服务开启主服务
 *
 * @author tom
 * @since 2013-8-3 上午12:29:06
 * @project app-backends-service
 * @package com.tangqiang.backends.app
 */
public class MainServer implements IMyServer{
	private Logger logger= Logger.getLogger(MainServer.class);
	private IAppConfigService appConfigService; 
	

	/**
	 * 启动所有的服务
	 * @throws Exception 
	 */
	public void startServer() {
		try {
			logger.info("******开始启动后台配置的服务******");
			//获取配置的服务
			List<TAppServerConfig> listServer = appConfigService.allAppServer();
			for (int i = 0; i < listServer.size(); i++) {
				TAppServerConfig serverConfig = listServer.get(i);
				if (serverConfig.isEnable()) {
					logger.info("准备启动服务:"+serverConfig.getTitle()+" 服务名:"+serverConfig.getServerName()+" 服务配置Bean:"+serverConfig.getServerBean());
					if (AppContext.getInstance().containsBean(serverConfig.getServerBean())) {
						IMyServer server = (IMyServer) AppContext.getInstance().getBean(serverConfig.getServerBean());
						server.startServer();
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("******启动后台服务时发生错误!!!******",e);
		}
	}


	public void stopServer(boolean bStopServer) {
		/** TODO Auto-generated method stub*/
		
	}


	public void restartServer() {
		/** TODO Auto-generated method stub*/
		
	}


	public void checkServerRunningStatus() {
		/** TODO Auto-generated method stub*/
		
	}


	public Thread getServerStatus() {
		/** TODO Auto-generated method stub*/
		return null;
	}


	public IAppConfigService getAppConfigService() {
		return appConfigService;
	}


	public void setAppConfigService(IAppConfigService appConfigService) {
		this.appConfigService = appConfigService;
	}


	
}
