package com.tangqiang.backends.controler.impl;

import java.awt.Robot;

import org.apache.log4j.Logger;

import com.tangqiang.backends.controler.IControler;
import com.tangqiang.backends.server.impl.mina.message.AbstrMessage;
import com.tangqiang.backends.server.impl.mina.message.MessageKeyBoardRequest;

/**
 *  键盘控制
 *
 * @author 汤强
 * @since 2013-8-2 下午5:49:11
 * @project app-backends-app
 * @package com.tangqiang.backends.controler.impl
 */
public class ControlKeyBoard  implements IControler {
	private Logger logger = Logger.getLogger(ControlKeyBoard.class);
	

	public void excute(AbstrMessage msg) throws Exception {
		try {
			Robot robot = new Robot();
			MessageKeyBoardRequest KeyMsg = (MessageKeyBoardRequest) msg;

			byte bCmd = KeyMsg.getCmd();
			int iKey = KeyMsg.getiKey();
			
			logger.info("------调用 键盘控制器执行方法, 键盘执行 按键:[" + iKey + "]执行命令:[" + bCmd + "]");
			
			if (bCmd == MessageKeyBoardRequest.KEY_PRESS) {
				robot.keyPress(iKey);
			} else if (bCmd == MessageKeyBoardRequest.KEY_RELEASE) {
				robot.keyRelease(iKey);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
