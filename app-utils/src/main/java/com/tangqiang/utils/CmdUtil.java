package com.tangqiang.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * cmd工具类
 *
 * @author 汤强
 * @since 2013-7-30 上午9:11:48
 * @project app-utils
 * @package com.tangqiang.utils
 */
public class CmdUtil {
	/**
	 * 执行一条命令
	 * @param sCmd 
	 * @return 结果
	 */
	public static String excuteCommand(String sCmd) {
		StringBuilder sbResult = new StringBuilder();
		Runtime r = Runtime.getRuntime();
		Process p;
		try {
			
			p = r.exec(sCmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline = "";
			while ((inline = br.readLine()) != null) {
				sbResult.append(inline);
			}
			br.close();
		} catch (Exception e) {
			return "执行命令:"+sCmd+" 出错!"+e.getMessage();
		}
		return sbResult.toString();
	}
	

}
