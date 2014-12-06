package com.tangqiang.backends.controler.impl;

import java.awt.Robot;

import org.apache.log4j.Logger;

import com.tangqiang.backends.app.SystemParams;
import com.tangqiang.backends.app.SystemParamsDef;
import com.tangqiang.backends.controler.IControler;
import com.tangqiang.backends.server.impl.mina.message.AbstrMessage;
import com.tangqiang.backends.server.impl.mina.message.MessageApplyRequest;
import com.tangqiang.utils.CmdUtil;
import com.tangqiang.utils.DateUtil;

/**
 * 控制一些应用程序
 *
 * @author 汤强
 * @since 2013-8-2 下午5:48:19
 * @project app-backends-app
 * @package com.tangqiang.backends.controler.impl
 */
public class ControlApply implements IControler {
	private Logger logger = Logger.getLogger(ControlApply.class);


	public void excute(AbstrMessage msg) throws Exception {
		try {
			MessageApplyRequest AppMsg = (MessageApplyRequest) msg;
			byte bApp = AppMsg.getbApp();
			byte cmd = AppMsg.getCmd();

			logger.debug("调用 应用控制器执行方法, 应用程序:[" + bApp + "]执行命令:[" + cmd + "]");
			// 根据应用执行不同的操作
			if (MessageApplyRequest.APP_SYSTEM == bApp) {
				if (MessageApplyRequest.APP_POUSE == cmd) {
					pouseComputer();
				}
			}else if (MessageApplyRequest.APP_KUGOU == bApp) {
				if (MessageApplyRequest.APP_START == cmd) {
					startMusic();
				} else if (MessageApplyRequest.APP_STOP == cmd) {
					stopMusic();
				} else if (MessageApplyRequest.APP_POUSE == cmd) {
					stopMusic();
				} else if (MessageApplyRequest.APP_NEXT_MUSIC == cmd) {
					nextMusic();
				}
			}
		} catch (Exception e) {
			logger.error("控制应用程序出现异常!"+e);
		}
	}
	
	/**
	 * 使电脑休眠
	 */
	private void pouseComputer() throws Exception{
		String sTime = DateUtil.format(60000l, "HH:mm");
		String sCmd = "at "+sTime+" rundll32.exe powrprof.dll,SetSuspendState";
		logger.info("执行使电脑休眠的命令:"+sCmd);
		String sResult = CmdUtil.excuteCommand(sCmd);
		logger.info("执行使电脑休眠结果:"+sResult);
	}
	
	private void stopComputer() throws Exception{
		String sCmd = "";
	}

	/**
	 * 开启音乐
	 * @throws Exception 
	 */
	private void startMusic() throws Exception {
		String sPathKugou = SystemParams.getSystemParams(SystemParamsDef.PATH_APP_KUGOU);
		String sCmd = "cmd /c " + sPathKugou;
		String sResult = CmdUtil.excuteCommand(sCmd);
		logger.info("执行开启音乐结果:"+sResult);
	}

	/**
	 * 停止音乐
	 * 
	 * @throws Exception
	 */
	private void stopMusic() throws Exception {
		logger.info("停止音乐! ctrl + alt + F5 ");
		Robot robot = new Robot();
		robot.keyPress(17);
		robot.keyPress(18);
		robot.keyPress(116);
		Thread.sleep(10l);
		robot.keyRelease(17);
		robot.keyRelease(18);
		robot.keyRelease(116);
		robot = null;
	}

	/**
	 * 下一首音乐
	 * 
	 * @throws Exception
	 */
	private void nextMusic() throws Exception {
		logger.info("下一首音乐! ctrl + alt + right ");
		Robot robot = new Robot();
		robot.keyPress(17);
		robot.keyPress(18);
		robot.keyPress(39);
		Thread.sleep(10l);
		robot.keyRelease(17);
		robot.keyRelease(18);
		robot.keyRelease(39);
		robot = null;
	}

}
