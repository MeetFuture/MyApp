package com.tangqiang.backends.server.impl.mina.message;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * 手机控制电脑 --- 鼠标 的请求信息
 * 
 * @author 汤强
 * @since 2013.01.24
 */
public class MessageMouseRequest extends MessageComputerControl {

	/**
	 * 鼠标命令
	 */

	/** 手机重力感应控制 */
	public static byte MOUSE_MOVE_GRAVITY = 0X01;
	/** 触摸控制 */
	public static byte MOUSE_MOVE_TOUCH = 0X02;
	/** 线性感应控制 */
	public static byte MOUSE_MOVE_LINER = 0X03;
	/** 某个键按下 */
	public static byte MOUSE_PRESS = 0X04;
	/** 键释放 */
	public static byte MOUSE_RELEASE = 0X05;
	/** 滚轮 */
	public static byte MOUSE_WHEEL = 0X06;

	/** 命令 */
	protected byte Cmd;

	/** 鼠标按键 */
	private int iKey = 0;
	/** 鼠标移动 */
	private float fVx = 0.00f;
	private float fVy = 0.00f;

	@Override
	public byte getModuleType() {
		return MOUSE_REQUEST;
	}

	@Override
	public int getLen(Charset charset) {
		/** 1个byte 1字节     2个float   8字节 一个long 8字节      一个int 4字节 */
		int len = 1 + 8 + 8 + 4;
		return len;
	}

	@Override
	public IoBuffer getEncode(Charset charset) throws Exception {
		if (Cmd == 0) {
			throw new Exception("请先定义鼠标所需执行的命令!");
		}
		// ===========编码数据区===============
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
		buf.put(getMsgType());
		buf.put(getModuleType());
		buf.putInt(getLen(charset));
		buf.put(Cmd);
		buf.putInt(iKey);
		buf.putLong(lSendTime);
		buf.putFloat(fVx);
		buf.putFloat(fVy);
		buf.flip();
		return buf;
	}

	@Override
	/**
	 * 自解码
	 */
	public void getDecode(IoBuffer buf, CharsetDecoder decoder) throws Exception {
		// 解析命令
		Cmd = buf.get();
		iKey = buf.getInt();
		lSendTime = buf.getLong();
		fVx = buf.getFloat();
		fVy = buf.getFloat();
	}

	public int getiKey() {
		return iKey;
	}

	public void setiKey(int iKey) {
		this.iKey = iKey;
	}

	public float getfVx() {
		return fVx;
	}

	public void setfVx(float fVx) {
		this.fVx = fVx;
	}

	public float getfVy() {
		return fVy;
	}

	public void setfVy(float fVy) {
		this.fVy = fVy;
	}

	public byte getCmd() {
		return Cmd;
	}

	public void setCmd(byte cmd) {
		Cmd = cmd;
	}

}
