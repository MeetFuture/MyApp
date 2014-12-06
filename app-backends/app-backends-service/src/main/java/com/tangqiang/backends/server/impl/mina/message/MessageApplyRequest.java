package com.tangqiang.backends.server.impl.mina.message;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * 应用控制的信息
 * 
 * @author tom
 * @since 2013-7-22
 */
public class MessageApplyRequest extends MessageComputerControl {

	/** 应用命令 */
	public static byte APP_START = 0X01;
	public static byte APP_STOP = 0X02;
	public static byte APP_POUSE = 0x03;
	public static byte APP_NEXT_MUSIC = 0x04;

	/** 系统 */
	public static byte APP_SYSTEM = 0X00;
	/** 酷狗 的id */
	public static byte APP_KUGOU = 0X01;

	/** 应用 */
	private byte bApp;
	/** 命令 */
	protected byte Cmd;

	@Override
	public byte getModuleType() {
		return APP_REQUEST;
	}

	@Override
	public int getLen(Charset charset) {
		return 10;
	}

	@Override
	public IoBuffer getEncode(Charset charset) throws Exception {
		if (bApp == 0) {
			throw new Exception("请先选取应用!");
		}
		if (Cmd == 0) {
			throw new Exception("请先定义所需执行的命令!");
		}
		// ===========编码数据区===============
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
		buf.put(getMsgType());
		buf.put(getModuleType());
		buf.putInt(getLen(charset));
		buf.put(bApp);
		buf.put(Cmd);
		buf.putLong(lSendTime);
		buf.flip();
		return buf;
	}

	@Override
	public void getDecode(IoBuffer buf, CharsetDecoder decoder) throws Exception {
		// 解析命令
		bApp = buf.get();
		Cmd = buf.get();
		lSendTime = buf.getLong();
	}

	public byte getbApp() {
		return bApp;
	}

	public void setbApp(byte bApp) {
		this.bApp = bApp;
	}

	public byte getCmd() {
		return Cmd;
	}

	public void setCmd(byte cmd) {
		Cmd = cmd;
	}

}
