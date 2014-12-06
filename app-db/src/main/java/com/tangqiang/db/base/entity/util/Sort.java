package com.tangqiang.db.base.entity.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.tangqiang.db.base.entity.enumaration.SortType;

/**
 * 排序操作解析 {@link ISortParser}
 * 
 * @author 汤强
 * @since 2013-7-30 下午5:18:07
 * @project app-db
 * @package com.tangqiang.db.base.entity.util
 */
public class Sort {

	private Map<String, Integer> sorters;

	private Sort() {
		sorters = new LinkedHashMap<String, Integer>();
	}
	
	/**
	 * 排序表
	 * @return
	 */
	public Map<String, Integer> getSorters() {
		return sorters;
	}

	public static Sort newInstance() {
		return new Sort();
	}

	public Sort asc(String propertyName) {
		sorters.put(propertyName, SortType.ASC.getValue());
		return this;
	}

	public Sort desc(String propertyName) {
		sorters.put(propertyName, SortType.DESC.getValue());
		return this;
	}
}
