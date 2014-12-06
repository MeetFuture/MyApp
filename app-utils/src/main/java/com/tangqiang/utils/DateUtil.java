package com.tangqiang.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理类
 *
 * @author 汤强
 * @since 2013-7-30 上午9:11:56
 * @project app-utils
 * @package com.tangqiang.utils
 */
public class DateUtil {

	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String YYYYMMDDHHMMSS0="yyyy-MM-dd HH:mm:ss";

	/**
	 * 把字符串转换成Date对象
	 * 
	 * @param datestr
	 * @param sFormat
	 *            日期模式，默认为"yyyy-MM-dd"
	 * @return Date对象
	 */
	public static Date parse(String datestr, String sFormat) {
		if (datestr == null || "".equals(datestr)) {
			return null;
		}
		if (sFormat == null || "".equals(sFormat)) {
			sFormat = "yyyy-MM-dd";
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		try {
			date = sdf.parse(datestr);
		} catch (Exception e) {
			return null;
		}
		return date;
	}

	/**
	 * 格式化日期至指定格式
	 * 
	 * @param date
	 *            日期（Date）
	 * @param sFormat
	 *            模式，如: yyyyMMdd HH:mm:ss
	 * @return 日期字符串
	 */
	public static String format(Date date, String sFormat) {
		if ("".equals(sFormat) || null == sFormat) {
			sFormat = "yyyyMMdd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		return sdf.format(date);
	}

	/**
	 * 获取指定格式 且与 当前日期相隔 day 天的日期字符串
	 * 
	 * @param iDay
	 *            与当前日期相隔天数---之前为减，之后为加
	 * @param sFormat
	 *            模式，如: yyyyMMdd HH:mm:ss
	 * @return 格式化的日期字符串
	 */
	public static String format(int iDay, String sFormat) {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		Date yesterday = new Date(today.getTime() + iDay * 24 * 3600 * 1000);
		return format(yesterday, sFormat);
	}

	/**
	 * 获取与当前时间相隔 lTime 微秒的时间
	 * 
	 * @param lTime
	 *            与当前时间相隔的微秒---之前为减，之后为加
	 * @param sFormat
	 *            模式，如: yyyyMMdd HH:mm:ss
	 * @return 格式化的日期字符串
	 */
	public static String format(long lTime, String sFormat) {
		Calendar calendar = Calendar.getInstance();
		Date nowDate = calendar.getTime();
		Date tmpDate = new Date(nowDate.getTime() + lTime);
		return format(tmpDate, sFormat);
	}

	/**
	 * 得到time的当天+(-)delta 这天的0点0分0秒0毫秒
	 * 
	 * @param time
	 * @return
	 */
	public static Date getDayBegin(long time, int delta) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(time);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.add(Calendar.DAY_OF_YEAR, delta);
		return new Date(calendar.getTimeInMillis());
	}

	/**
	 * 得到time的当天+(-)delta 这天的23点59分59秒999毫秒
	 * 
	 * @param time
	 * @return
	 */
	public static Date getDayEnd(long time, int delta) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(time);
		calendar.set(Calendar.MILLISECOND, 999);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.add(Calendar.DAY_OF_YEAR, delta);
		return new Date(calendar.getTimeInMillis());
	}

	/**
	 * 获取系统当前日期
	 * 
	 * @return 系统当前日期
	 */
	public static String getNowDate() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(today);
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return 系统当前日期
	 */
	public static String getNowTime() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("HHmmss");
		return format.format(today);
	}

	/**
	 * 获取指定格式的当前时间
	 * 
	 * @param sFormat
	 *            格式 (默认:yyyy-MM-dd HH:mm:ss) 可以为: [yyyy年MM月dd日HH时mm分ss秒] [yyyyMMdd] 等
	 * @return
	 */
	public static String getDateTime(String sFormat) {
		if (sFormat == null || "".equals(sFormat)) {
			sFormat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		return sdf.format(new Date());
	}
}
