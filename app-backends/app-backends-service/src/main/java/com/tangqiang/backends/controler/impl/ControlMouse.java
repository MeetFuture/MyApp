package com.tangqiang.backends.controler.impl;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import org.apache.log4j.Logger;

import com.tangqiang.backends.controler.IControler;
import com.tangqiang.backends.server.impl.mina.message.AbstrMessage;
import com.tangqiang.backends.server.impl.mina.message.MessageMouseRequest;

/**
 * 控制鼠标
 *
 * @author 汤强
 * @since 2013-8-2 下午5:49:01
 * @project app-backends-app
 * @package com.tangqiang.backends.controler.impl
 */
public class ControlMouse  implements IControler {
	private Logger logger = Logger.getLogger(ControlMouse.class);
	private long lLastTime = 0;

	public void excute(AbstrMessage msg) throws Exception {
		Robot robot = new Robot();
		MessageMouseRequest MouseMsg = (MessageMouseRequest) msg;

		long lSendTime = MouseMsg.getlSendTime();
		byte bCmd = MouseMsg.getCmd();
		if (bCmd == MessageMouseRequest.MOUSE_MOVE_GRAVITY) {
			long lDTime = lSendTime - lLastTime;

			if (lDTime < 100) {
				int x = 0;
				int y = 0;
				/** 采用平方处理 使鼠标的移动 与 重力的2次方成正比 */
				float fVxNow = MouseMsg.getfVx();
				float fVyNow = MouseMsg.getfVy();

				fVxNow = fVxNow > 0 ? fVxNow + 1f : fVxNow - 1f;
				fVyNow = fVyNow > 0 ? fVyNow + 1f : fVyNow - 1f;
				x = fVxNow > 0 ? (int) (Math.pow(((fVxNow)), 2) / 2) : -(int) (Math.pow(((fVxNow)), 2) / 2);
				y = fVyNow > 0 ? (int) (Math.pow(((fVyNow)), 2) / 2) : -(int) (Math.pow(((fVyNow)), 2) / 2);

				Point pPre = MouseInfo.getPointerInfo().getLocation();
				int xMove = pPre.x + x;
				int yMove = pPre.y + y;
				robot.mouseMove(xMove, yMove);
			}
			lLastTime = lSendTime;

		} else if (bCmd == MessageMouseRequest.MOUSE_MOVE_TOUCH) {
			Point pPre = MouseInfo.getPointerInfo().getLocation();
			int xMove = (int) (pPre.x + MouseMsg.getfVx());
			int yMove = (int) (pPre.y + MouseMsg.getfVy());
			robot.mouseMove(xMove, yMove);
		} else if (bCmd == MessageMouseRequest.MOUSE_PRESS) {
			/** 鼠标某个 按键按下 */
			// InputEvent.BUTTON1_MASK = 16 (0X10)
			logger.debug("鼠标键 :" + MouseMsg.getiKey() + " 按下!");
			robot.mousePress(MouseMsg.getiKey());
		} else if (bCmd == MessageMouseRequest.MOUSE_RELEASE) {
			/** 鼠标某个 按键释放 */
			// InputEvent.BUTTON2_MASK = 8 (0X8)
			logger.debug("鼠标键 :" + MouseMsg.getiKey() + " 释放!");
			robot.mouseRelease(MouseMsg.getiKey());
		} else if (bCmd == MessageMouseRequest.MOUSE_WHEEL) {
			logger.debug("鼠标滚轮:" + MouseMsg.getiKey());
			robot.mouseWheel(MouseMsg.getiKey());
		}
	}

}
