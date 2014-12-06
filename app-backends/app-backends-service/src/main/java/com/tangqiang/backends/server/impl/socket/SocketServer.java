package com.tangqiang.backends.server.impl.socket;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.tangqiang.backends.app.SystemParams;
import com.tangqiang.backends.app.SystemParamsDef;
import com.tangqiang.backends.server.IMyServer;

/**
 * socket服务启动程序
 *
 * @author tom
 * @since 2013-8-3 上午10:46:11
 * @project app-backends-service
 * @package com.tangqiang.backends.server.impl.socket
 */
public class SocketServer extends Thread implements IMyServer {
	private final Logger log = Logger.getLogger(SocketServer.class);
	private int iPort;
	private int iMinaPort;

	public void run() {
		try {
			init();
			InetAddress i = InetAddress.getLocalHost();
			String sIP = i.getHostAddress();
			log.info("------开始开启socket服务，服务IP:" + sIP + " 服务端口:" + iPort + "------");
			ServerSocket s = new ServerSocket(iPort);
			log.info("------socket服务开启成功！等待客户连接------");
			while (true) {
				Socket sc = s.accept();
				DealThread dt = new DealThread(sc, iMinaPort);
				dt.start();
			}
		} catch (Exception e) {
			log.error("------开启socket服务出现异常!", e);
		}
	}
	
	private void init() {
		try {
			iPort = Integer.parseInt(SystemParams.getSystemParams(SystemParamsDef.PORT_SOCKET));
			iMinaPort = Integer.parseInt(SystemParams.getSystemParams(SystemParamsDef.PORT_MINA));
		} catch (Exception e) {
			log.error("初始化socket服务参数失败!", e);
		}
	}

	public void startServer() {
		this.start();
	}

	public void stopServer(boolean bStopServer) {
		// TODO Auto-generated method stub

	}

	public void restartServer() {
		// TODO Auto-generated method stub

	}

	public void checkServerRunningStatus() {
		// TODO Auto-generated method stub

	}

	public Thread getServerStatus() {
		// TODO Auto-generated method stub
		return null;
	}
}
