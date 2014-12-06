package com.tangqiang.backends.server.impl.mina.message;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * 键盘请求信息
 * 
 * @author tom
 * 
 */
public class MessageKeyBoardRequest extends MessageComputerControl {

	/** 键盘按键 */
	public static byte KEY_PRESS = 0X01;
	/** 键盘释放 */
	public static byte KEY_RELEASE = 0X02;

	/** 命令 */
	protected byte Cmd;
	/** 按键ask码 */
	private int iKey = 0;

	@Override
	public byte getModuleType() {
		return KEY_REQUEST;
	}

	@Override
	public int getLen(Charset charset) {
		// 1个byte 一个 int 1个float
		return 1 + 4 + 8;
	}

	@Override
	public IoBuffer getEncode(Charset charset) throws Exception {
		if (Cmd == 0) {
			throw new Exception("请先定义键盘所需执行的命令!");
		}
		// ===========编码数据区===============
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
		buf.put(getMsgType());
		buf.put(getModuleType());
		buf.putInt(getLen(charset));
		buf.put(Cmd);
		buf.putInt(iKey);
		buf.putLong(lSendTime);
		buf.flip();
		return buf;
	}

	@Override
	public void getDecode(IoBuffer buf, CharsetDecoder decoder) throws Exception {
		// 解析命令
		Cmd = buf.get();
		iKey = buf.getInt();
		lSendTime = buf.getLong();
	}

	public int getiKey() {
		return iKey;
	}

	public void setiKey(int iKey) {
		this.iKey = iKey;
	}

	public byte getCmd() {
		return Cmd;
	}

	public void setCmd(byte cmd) {
		Cmd = cmd;
	}

}
