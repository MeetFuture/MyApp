package com.tangqiang.backends.controler.impl;

import org.apache.log4j.Logger;

import com.tangqiang.backends.controler.IControler;
import com.tangqiang.backends.server.impl.mina.message.AbstrMessage;
import com.tangqiang.backends.server.impl.mina.message.MessageApplyRequest;
import com.tangqiang.backends.server.impl.mina.message.MessageKeyBoardRequest;
import com.tangqiang.backends.server.impl.mina.message.MessageMouseRequest;

/**
 * 电脑控制 器
 * 
 * @author 汤强
 * @since 2013-8-2 下午5:36:14
 * @project app-backends-app
 * @package com.tangqiang.backends.controler.impl
 */
public class ComputerControler implements IControler {
	private Logger logger = Logger.getLogger(ComputerControler.class);

	private IControler mouseControler;
	private IControler keyBoardControler;
	private IControler applyControler;

	public void excute(AbstrMessage msg) throws Exception {
		logger.debug("----开始调用 电脑控制器 执行方法!");
		if (msg instanceof MessageMouseRequest) {
			mouseControler.excute(msg);
		} else if (msg instanceof MessageKeyBoardRequest) {
			keyBoardControler.excute(msg);
		} else if (msg instanceof MessageApplyRequest) {
			applyControler.excute(msg);
		}
	}

	public IControler getMouseControler() {
		return mouseControler;
	}

	public void setMouseControler(IControler mouseControler) {
		this.mouseControler = mouseControler;
	}

	public IControler getKeyBoardControler() {
		return keyBoardControler;
	}

	public void setKeyBoardControler(IControler keyBoardControler) {
		this.keyBoardControler = keyBoardControler;
	}

	public IControler getApplyControler() {
		return applyControler;
	}

	public void setApplyControler(IControler applyControler) {
		this.applyControler = applyControler;
	}

}
