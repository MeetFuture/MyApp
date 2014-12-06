package com.tangqiang.backends.server.impl.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * 处理 socket 连接的线程
 *
 * @author tom
 * @since 2013-8-3 上午10:46:29
 * @project app-backends-service
 * @package com.tangqiang.backends.server.impl.socket
 */
public class DealThread extends Thread {
	private Logger logger = Logger.getLogger(DealThread.class);

	private Socket sc;
	private int iPort;

	public DealThread(Socket sc,int iPort) {
		this.sc = sc;
		this.iPort = iPort;
	}

	@Override
	public void run() {
		try {
			logger.info("开启一个socket接收数据线程,网络地址:"+sc.getInetAddress());
			InputStream ins = sc.getInputStream();
			DataInputStream bins = new DataInputStream(ins);
			DataOutputStream bous = getOutPut();
			while (true) {
				int i = bins.available();
				byte[] bArrData = new byte[i];
				bins.read(bArrData);
				bous.write(bArrData);
			}
		} catch (Exception e) {
			logger.error("socket接收转发数据时出现异常!");
		}
	}

	/**
	 * 获取转发输出流
	 * 
	 * @return
	 * @throws Exception
	 */
	private DataOutputStream getOutPut() throws Exception {
		Socket sc = new Socket("localhost", iPort);
		OutputStream ous = sc.getOutputStream();
		return new DataOutputStream(ous);
	}
}
