package com.tangqiang.db.base.pagination;

import java.util.List;

/**
 * 分页接口
 * 
 * @author 汤强
 * 
 * @param <E>
 */
public interface IPagination<E> {

	/**
	 * <p>
	 * get pagination object's data items
	 * 
	 * @return
	 */
	public List<E> getItems();

	/**
	 * <p>
	 * set pagination object's data items
	 * 
	 * @param items
	 */
	public void setItems(List<E> items);

	/**
	 * <p>
	 * get total count of data items
	 * 
	 * @return
	 */
	public long getTotalCount();

	/**
	 * <p>
	 * set total count of data items
	 * 
	 * @param count
	 */
	public void setTotalCount(long count);

	/**
	 * <p>
	 * get pagination object's page from
	 * 
	 * @return
	 */
	public int getFrom();

	/**
	 * <p>
	 * set pagination object's page from
	 * 
	 * @param from
	 */
	public void setFrom(int from);

	/**
	 * <p>
	 * get pagination object's page scope,which work with 'from' to specify<br>
	 * the data in page
	 * 
	 * @return
	 */
	public int getScope();

	/**
	 * <p>
	 * set pagination object's page scope,which work with 'from' to specify<br>
	 * the data in page
	 * 
	 * @param scope
	 */
	public void setScope(int scope);
}
