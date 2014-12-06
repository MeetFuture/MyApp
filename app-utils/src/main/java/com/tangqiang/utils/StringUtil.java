package com.tangqiang.utils;

/**
 * 字符串工具
 * 
 * @author 汤强
 * @since 2013-7-30 上午9:09:17
 * @project app-utils
 * @package com.tangqiang.utils
 */
public class StringUtil {

	/**
	 * 截取 "tag" 之前的字符串
	 * 
	 * @param sSource 资源字符串
	 * @param sTag 分隔字符串
	 * @return
	 */
	public static String substringBefore(String sSource, String sTag) {
		return sSource.substring(sSource.indexOf(sTag));
	}

}
