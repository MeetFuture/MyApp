package com.tangqiang.backends.server;


/**
 * 后台服务接口
 *
 * @author tom
 * @since 2013-8-2 下午7:35:37
 * @project app-backends-service
 * @package com.tangqiang.backends.server
 */
public interface IMyServer {
	
	/**
	 * 开启服务
	 */
	public void startServer();
	
	
	public void stopServer(boolean bStopServer) ;
	
	public void restartServer();
	
	public void checkServerRunningStatus() ;
	
	public Thread getServerStatus() ;

}
