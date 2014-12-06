package com.tangqiang.backends.controler;

import com.tangqiang.backends.server.impl.mina.message.AbstrMessage;

/**
 * 控制器接口
 * @author 汤强
 * @since 2013-7-22
 */
public interface IControler {
	
	/**
	 * 控制执行的方法
	 * @param msg 信息
	 */
	public void excute(AbstrMessage msg) throws Exception;
}
