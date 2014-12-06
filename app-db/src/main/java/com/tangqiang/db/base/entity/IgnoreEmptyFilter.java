package com.tangqiang.db.base.entity;

import java.util.Collection;
import java.util.List;

/**
 * 忽略空值过滤器实现 实现接口{@link IFilter}
 *
 * @author 汤强
 * @since 2013-7-30 下午7:20:03
 * @project app-db
 * @package com.tangqiang.db.base.entity
 */
public class IgnoreEmptyFilter implements IFilter {

	public boolean isFilterEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public IFilter and(IFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter or(IFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter eq(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter eqIgnoreCase(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter ne(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter neIgnoreCase(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter gt(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter ge(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter lt(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter le(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter between(String fieldName, Object lVlaue, Object rValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter notBetween(String fieldName, Object lVlaue, Object rValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter in(String fieldName, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter notIn(String fieldName, Collection<?> values) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter in(String fieldName, Collection<?> values) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter notIn(String fieldName, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter empty(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter notEmpty(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter isNull(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter notNull(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter like(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFilter notLike(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FilterCase> getFilterCases() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
