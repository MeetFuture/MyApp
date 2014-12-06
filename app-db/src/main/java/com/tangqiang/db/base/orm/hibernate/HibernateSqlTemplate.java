package com.tangqiang.db.base.orm.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.tangqiang.db.base.orm.ISqlTemplate;

/**
 * hibernate数据操作sql组装类
 * 
 * @author 汤强
 * 
 */
public class HibernateSqlTemplate implements ISqlTemplate {

	/** the database key word <b>set</b> */
	private static final String KEYWORD_SET = " set ";
	/** the database key word <b>=</b> */
	private static final String KEYWORD_EQ = " = ";
	/** null string in sql */
	private static final String KEYWORD_NULL = " null ";
	/** single quote string */
	private static final String KEYWORD_SINGLE_QUOTE = "'";
	/** comma string */
	private static final String KEYWORD_COMMA = ",";
	/** instance */
	public static ISqlTemplate DEFAULT = new HibernateSqlTemplate();

	public String updateSetString(Map<String, Object> params) {
		if (params == null || params.isEmpty())
			return "";
		StringBuffer sb = new StringBuffer(KEYWORD_SET);
		Set<String> keySet = params.keySet();
		boolean isFirstKey = true;
		for (String key : keySet) {
			if (isFirstKey) {
				isFirstKey = false;
			} else {
				sb.append(KEYWORD_COMMA);
			}

			Object value = params.get(key);
			if (value == null) {
				sb.append(" ").append(key).append(KEYWORD_EQ).append(KEYWORD_NULL);
			} else if (value instanceof Date) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sb.append(" ").append(key).append(KEYWORD_EQ).append(KEYWORD_SINGLE_QUOTE).append(sf.format((Date) value)).append(KEYWORD_SINGLE_QUOTE);
			} else if (value instanceof String) {
				sb.append(" ").append(key).append(KEYWORD_EQ).append(KEYWORD_SINGLE_QUOTE).append(value).append(KEYWORD_SINGLE_QUOTE);
			} else {
				sb.append(" ").append(key).append(KEYWORD_EQ).append(value);
			}
		}
		return sb.toString();
	}

}
