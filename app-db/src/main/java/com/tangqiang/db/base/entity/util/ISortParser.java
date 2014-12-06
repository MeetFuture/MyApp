package com.tangqiang.db.base.entity.util;

/**
 * 排序解析接口 {@link Sort}
 * 
 * @author 汤强
 * @since 2013-7-30 下午5:19:11
 * @project app-db
 * @package com.tangqiang.db.base.entity.util
 */
public interface ISortParser {

	public static final String ASC = "asc";
	public static final String DESC = "desc";

	/**
	 * 解析方法
	 * 
	 * @param sort
	 * @return
	 */
	public String parse(Sort sort);
}
