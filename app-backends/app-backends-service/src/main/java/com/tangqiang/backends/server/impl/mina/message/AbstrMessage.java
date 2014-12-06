package com.tangqiang.backends.server.impl.mina.message;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * mina 信息的抽象类 ( byte 取值在十六进制中为: -0X80^0X7F -128^127)
 * 
 * @author 汤强
 * @since 2013.07.24
 */
public abstract class AbstrMessage {

	/** 控制电脑协议 */
	public static byte COMPUTER_CONNTROL = 0X11;
	
	
	/**
	 * 协议编号
	 * 
	 * @return
	 */
	public abstract byte getMsgType();

	/**
	 * 获取模块信息
	 * 
	 * @return
	 */
	public abstract byte getModuleType();

	/**
	 * 数据区长度
	 * 
	 * @param charset
	 * @return
	 */
	public abstract int getLen(Charset charset);

	/**
	 * 信息的 自编码方式
	 * 
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public abstract IoBuffer getEncode(Charset charset) throws Exception;

	/**
	 * 信息的 自解码方式
	 * 
	 * @param buf
	 * @param decoder
	 * @throws Exception
	 */
	public abstract void getDecode(IoBuffer buf, CharsetDecoder decoder)
			throws Exception;
}
