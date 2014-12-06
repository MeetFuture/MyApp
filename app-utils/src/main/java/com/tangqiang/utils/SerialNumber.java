package com.tangqiang.utils;

import java.util.Date;

/**
 * 获取唯一序列
 *
 * @author 汤强
 * @since 2013-7-30 上午9:12:03
 * @project app-utils
 * @package com.tangqiang.utils
 */
public class SerialNumber {

	private static final int SERIAL_START = 10;
	private static final int SERIAL_END = 99;
	private static int SERIAL = SERIAL_START;

	public static String GET() {
		String dateMsg = DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS);
		return dateMsg + Integer.valueOf(next()).toString();
	}

	private static synchronized int next() {
		if (SERIAL >= SERIAL_END)
			SERIAL = SERIAL_START;
		SERIAL++;
		return SERIAL;
	}
}
