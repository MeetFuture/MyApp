package com.tangqiang.backends.controler.impl;

import org.apache.log4j.Logger;

import com.tangqiang.backends.controler.IControler;
import com.tangqiang.backends.server.impl.mina.message.AbstrMessage;
import com.tangqiang.backends.server.impl.mina.message.MessageComputerControl;

/**
 * 主控制器---所有控制器的父类
 * 
 * @author 汤强
 * @since 2013-8-2 下午5:33:27
 * @project app-backends-app
 * @package com.tangqiang.backends.controler
 */
public class MainControler implements IControler {
	private Logger logger = Logger.getLogger(MainControler.class);

	/** 电脑控制器 */
	private IControler computerControler;

	public void excute(AbstrMessage msg) throws Exception {
		logger.debug("----开始调用  主控制器 执行方法!");
		if (msg instanceof MessageComputerControl) {
			computerControler.excute(msg);
		} else {
			logger.error("--主控制器未能正确识别消息!");
			throw new Exception("主控制器未能正确识别消息!");
		}
	}

	public IControler getComputerControler() {
		return computerControler;
	}

	public void setComputerControler(IControler computerControler) {
		this.computerControler = computerControler;
	}
}
