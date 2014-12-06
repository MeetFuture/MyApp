package com.tangqiang.backends.server.impl.mina.message;


/**
 * 电脑控制信息
 * 
 * @author 汤强
 * 
 */
public abstract class MessageComputerControl extends AbstrMessage {
	
	/** 鼠标模块 */
	public static byte MOUSE_REQUEST = 0X01;
	/** 键盘模块 */
	public static byte KEY_REQUEST = 0X02;
	/**应用控制模块*/
	public static byte APP_REQUEST = 0X03;
	
	
	/** 发送时间 */
	protected long lSendTime;

	@Override
	public byte getMsgType() {
		return COMPUTER_CONNTROL;
	}


	public long getlSendTime() {
		return lSendTime;
	}

	public void setlSendTime(long lSendTime) {
		this.lSendTime = lSendTime;
	}

}
