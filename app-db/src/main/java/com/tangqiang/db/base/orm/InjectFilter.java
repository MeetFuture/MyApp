package com.tangqiang.db.base.orm;

/**
 * 避免注入工具类
 *
 * @author 汤强
 * @since 2013-7-30 下午7:29:33
 * @project app-db
 * @package com.tangqiang.db.base.orm
 */
public class InjectFilter {

	/**
	 * 避免sql注入
	 * 
	 * @param param
	 * @return
	 */
	public static String filterSqlInject(String param) {
		InvalidSqlChar[] invalids = InvalidSqlChar.values();
		for (InvalidSqlChar c : invalids) {
			param = param.replaceAll(c.getValue().invalid, c.getValue().valid);
		}
		return param;
	}

	/**
	 * 非法sql字符
	 * 
	 * @author 汤强
	 * 
	 */
	public static enum InvalidSqlChar {
		SINGLE_QUOTE(new Pair("'", "''")), COMMENT(new Pair("--", ""));

		private Pair value;

		public Pair getValue() {
			return value;
		}

		private InvalidSqlChar(Pair value) {
			this.value = value;
		}
	}

	/**
	 * 非法sql字符对
	 * 
	 * @author 汤强
	 * 
	 */
	private static class Pair {
		private String invalid;
		private String valid;

		public Pair(String invalid, String valid) {
			this.invalid = invalid;
			this.valid = valid;
		}
	}

	// 测试主函数
	public static void main(String args[]) {
		System.out.println(InjectFilter.filterSqlInject("ha'llo--h-a"));
	}

}
