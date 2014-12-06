package com.tangqiang.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 基础公共处理函数类
 *
 * @author 汤强
 * @since 2013-7-30 上午9:11:37
 * @project app-utils
 * @package com.tangqiang.utils
 */
public class BaseUtil {
	private static Logger log = Logger.getLogger(BaseUtil.class);
	
	/**
	 * 
	 * @return
	 */
	public String getMethodMessage() {
		StackTraceElement stack[] = Thread.currentThread().getStackTrace();
		// 取最外层调用的类的信息
		String tag = "";
		for (int i = 0; i < stack.length; i++) {
			if (stack[i].getFileName().equals("BaseUtil.java")) {
				tag = stack[i + 1].getFileName() + ":" + stack[i + 1].getMethodName();
				break;
			}
		}
		return tag;
	}

	/**
	 * 判断字符串是否数字
	 * 
	 * @param strMessage
	 * 
	 * @return 是数字 true 否 false
	 */
	public static boolean isNumber(String sNum) {
		log.info("开始判断字符串:"+sNum+" 是否为数字!");
		int iLength = sNum.length();
		// 将所有非数字字符改为 "" ( 小数点除外)
		String sNumber = sNum.replaceAll("[^\\d^.]", "");
		if (iLength == sNumber.length()) {
			try {
				Double.parseDouble(sNumber);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * 判断一字符串是否是IP
	 * 
	 * @param sIP
	 * @return
	 */
	public static boolean isIP(String sIP) {
		String sReg = "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])";
		Pattern pattern = Pattern.compile(sReg);
		Matcher matcher = pattern.matcher(sIP);
		boolean bResult = matcher.matches();
		return bResult;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 格式为:yyyy-MM-dd HH:mm:ss 的当前时间
	 */
	public static String getTime() {
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		return time;
	}

	/** ************ test only ************* */
	public static void main(String[] args) {
			System.out.println(BaseUtil.isNumber("asd"));
	}
}
