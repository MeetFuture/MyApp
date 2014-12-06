package com.tangqiang.backends.server.impl.mina;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.tangqiang.backends.app.SystemParams;
import com.tangqiang.backends.app.SystemParamsDef;
import com.tangqiang.backends.server.IMyServer;

/**
 * mina服务器 
 *
 * @author tom
 * @since 2013-8-2 下午7:39:33
 * @project app-backends-service
 * @package com.tangqiang.backends.server.impl.mina
 */
public class MinaServer extends Thread implements IMyServer {
	private static Logger log = Logger.getLogger(MinaServer.class);
	
	private ProtocolCodecFactory protocolCodecFactory;
	private IoHandlerAdapter ioHandlerAdapter;
	

	private Integer iPort;

	public void run() {
		IoAcceptor acceptor = null;
		try {
			iPort = Integer.parseInt(SystemParams.getSystemParams(SystemParamsDef.PORT_MINA));
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress();
			log.info("------开始开启Mina服务，服务IP:" + ip + " 服务端口:" + iPort + "------");

			// 创建一个非阻塞的server端的Socket
			acceptor = new NioSocketAcceptor();
			// 设置过滤器（添加自带的编解码器）
			acceptor.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(protocolCodecFactory));
			// 设置日志过滤器
			LoggingFilter lf = new LoggingFilter();
			lf.setMessageReceivedLogLevel(LogLevel.DEBUG);
			acceptor.getFilterChain().addLast("logger", lf);
			// 获得IoSessionConfig对象
			IoSessionConfig cfg = acceptor.getSessionConfig();
			// 读写通道10秒内无操作进入空闲状态
			cfg.setIdleTime(IdleStatus.BOTH_IDLE, 100);
			// 绑定逻辑处理器
			acceptor.setHandler(ioHandlerAdapter);
			// 绑定端口
			acceptor.bind(new InetSocketAddress(iPort));

			log.info("------mina 服务开启成功！等待客户连接------");
		} catch (Exception e) {
			log.error("------mina 服务端启动异常....", e);
		}
	}

	public void startServer() {
		this.start();
	}

	public boolean stopServer() {
		// TODO Auto-generated method stub
		return false;
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



	public IoHandlerAdapter getIoHandlerAdapter() {
		return ioHandlerAdapter;
	}

	public void setIoHandlerAdapter(IoHandlerAdapter ioHandlerAdapter) {
		this.ioHandlerAdapter = ioHandlerAdapter;
	}

	public ProtocolCodecFactory getProtocolCodecFactory() {
		return protocolCodecFactory;
	}

	public void setProtocolCodecFactory(ProtocolCodecFactory protocolCodecFactory) {
		this.protocolCodecFactory = protocolCodecFactory;
	}



}