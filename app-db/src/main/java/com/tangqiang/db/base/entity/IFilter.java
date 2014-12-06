package com.tangqiang.db.base.entity;

import java.util.Collection;
import java.util.List;

import com.tangqiang.db.base.entity.enumaration.Operator;
import com.tangqiang.db.base.entity.enumaration.Relation;

/**
 * 过滤器接口
 *
 * @author 汤强
 * @since 2013-7-30 下午7:15:49
 * @project app-db
 * @package com.tangqiang.db.base.entity
 */
public interface IFilter {

	/** left value key for map */
	public static final String LEFT_VALUE = "l_v";

	/** right value key for map */
	public static final String RIGHT_VALUE = "r_v";

	/**
	 * 确认过滤器是否为空
	 * 
	 * @return
	 */
	public boolean isFilterEmpty();

	/**
	 * 和
	 * 
	 * @param filter
	 * @return
	 */
	public IFilter and(IFilter filter);

	/**
	 * 与
	 * 
	 * @param filter
	 * @return
	 */
	public IFilter or(IFilter filter);

	/**
	 * 等
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter eq(String fieldName, Object value);

	/**
	 * 忽略大小 的 等(value字符串)
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter eqIgnoreCase(String fieldName, Object value);

	/**
	 * 不等
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter ne(String fieldName, Object value);

	/**
	 * 忽略大小写的 不等(value字符串)
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter neIgnoreCase(String fieldName, Object value);

	/**
	 * 大于
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter gt(String fieldName, Object value);

	/**
	 * 大于等于
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter ge(String fieldName, Object value);

	/**
	 * 小于
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter lt(String fieldName, Object value);

	/**
	 * 小于等于
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter le(String fieldName, Object value);

	/**
	 * between
	 * 
	 * @param fieldName
	 * @param lVlaue
	 * @param rValue
	 * @param <code>comparator</code>
	 * @return
	 */
	public IFilter between(String fieldName, Object lVlaue, Object rValue);

	/**
	 * not between
	 * 
	 * @param fieldName
	 * @param lVlaue
	 * @param rValue
	 * @param <code>comparator</code>
	 * @return
	 */
	public IFilter notBetween(String fieldName, Object lVlaue, Object rValue);

	/**
	 * in
	 * 
	 * @param fieldName
	 * @param values
	 * @param comparator
	 * @return
	 */
	public IFilter in(String fieldName, Object[] values);

	/**
	 * not in
	 * 
	 * @param fieldName
	 * @param values
	 * @param comparator
	 * @return
	 */
	public IFilter notIn(String fieldName, Collection<?> values);

	/**
	 * in
	 * 
	 * @param fieldName
	 * @param values
	 * @param comparator
	 * @return
	 */
	public IFilter in(String fieldName, Collection<?> values);

	/**
	 * not in
	 * 
	 * @param fieldName
	 * @param values
	 * @param comparator
	 * @return
	 */
	public IFilter notIn(String fieldName, Object[] values);

	/**
	 * empty
	 * 
	 * @param fieldName
	 * @return
	 */
	public IFilter empty(String fieldName);

	/**
	 * not empty
	 * 
	 * @param fieldName
	 * @return
	 */
	public IFilter notEmpty(String fieldName);

	/**
	 * null
	 * 
	 * @param fieldName
	 * @return
	 */
	public IFilter isNull(String fieldName);

	/**
	 * not null
	 * 
	 * @param fieldName
	 * @return
	 */
	public IFilter notNull(String fieldName);

	/**
	 * like
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter like(String fieldName, Object value);

	/**
	 * not like
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter notLike(String fieldName, Object value);

	/**
	 * 获取过滤器的过滤方式
	 * @return
	 */
	public List<FilterCase> getFilterCases();

	/**
	 * 不同操作的定义
	 * 
	 * @author 汤强
	 * 
	 */
	public static class FilterCase {

		public enum CaseType {
			UNIT("unit"), GROUP("group");
			private String value;

			public String getValue() {
				return value;
			}

			private CaseType(String value) {
				this.value = value;
			}

		}

		/** the type of filter */
		private Operator filterType;

		/** and ,or */
		private Relation groupType;

		/** case type,it can be GROUP or UNIT */
		private CaseType caseType;

		/** the field name to filter */
		private String fieldName;

		/** filter value */
		private Object value;

		public Operator getFilterType() {
			return filterType;
		}

		public void setFilterType(Operator filterType) {
			this.filterType = filterType;
		}

		public Relation getGroupType() {
			return groupType;
		}

		public void setGroupType(Relation groupType) {
			this.groupType = groupType;
		}

		public CaseType getCaseType() {
			return caseType;
		}

		public void setCaseType(CaseType caseType) {
			this.caseType = caseType;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

	};

}
