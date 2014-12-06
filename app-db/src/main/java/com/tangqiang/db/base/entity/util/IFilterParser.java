package com.tangqiang.db.base.entity.util;

import com.tangqiang.db.base.entity.IFilter;

/**
 * 过滤器解析接口
 *
 * @author 汤强
 * @since 2013-7-30 下午5:19:58
 * @project app-db
 * @package com.tangqiang.db.base.entity.util
 */
public interface IFilterParser {

	/**
	 * 将过滤器解析
	 * 
	 * @param filter
	 * @return 解析后的过滤语句
	 * @throws Exception
	 */
	public String parse(IFilter filter) throws Exception;
}
