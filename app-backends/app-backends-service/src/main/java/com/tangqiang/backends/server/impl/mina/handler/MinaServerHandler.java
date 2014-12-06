package com.tangqiang.backends.server.impl.mina.handler;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.tangqiang.backends.controler.impl.MainControler;
import com.tangqiang.backends.server.impl.mina.message.AbstrMessage;

/**
 * mina 服务端处理器
 * 
 * @author 汤强
 * @since 2013-8-2 下午5:31:24
 * @project app-backends-app
 * @package com.tangqiang.backends.server.impl.mina.handler
 */
public class MinaServerHandler extends IoHandlerAdapter {

	private Logger logger = Logger.getLogger(MinaServerHandler.class);
	private MainControler mainControler;

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("服务端与客户端创建连接..客户地址:" + session.getRemoteAddress() + "  sission id:" + session.getId());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("Sission id:" + session.getId() + " 服务端与客户端连接打开...");
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		if (message instanceof AbstrMessage) {
			mainControler.excute((AbstrMessage) message);
		} else {
			logger.info("Sission id:" + session.getId() + " 未知请求！");
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.info("Sission id:" + session.getId() + " 服务端发送信息成功...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("Sission id:" + session.getId() + " 会话关闭了...");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		logger.info("Sission id:" + session.getId() + " 服务端进入空闲状态...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.error("Sission id:" + session.getId() + " mina服务端出现异常:" + cause.getMessage());
	}

	public MainControler getMainControler() {
		return mainControler;
	}

	public void setMainControler(MainControler mainControler) {
		this.mainControler = mainControler;
	}

}