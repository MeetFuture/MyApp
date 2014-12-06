package com.tangqiang.db.base.orm;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.tangqiang.db.base.entity.IFilter;
import com.tangqiang.db.base.entity.util.Sort;
import com.tangqiang.db.base.pagination.IPagination;

/**
 * 公用数据操作接口
 * 
 * @author 汤强
 * @since 2013-7-30 下午7:47:23
 * @project app-db
 * @package com.tangqiang.db.base.orm
 * @param <E>
 *            数据库对应实体
 * @param <PK>
 *            唯一序列号
 */
public interface IDao<E, PK extends Serializable> {
	/**
	 * <p>
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public Connection connect();

	/**
	 * <p>
	 * 获取数据源
	 * 
	 * @return
	 */
	public DataSource getDataSource();

	/**
	 * 根据id 查找一个实体,不支持懒加载
	 * 
	 * @param pk
	 * @return
	 */
	public E get(PK pk);

	/**
	 * 根据id 查找一个实体,支持懒加载
	 * 
	 * @param pk
	 * @return
	 */
	public E load(PK pk);

	/**
	 * 保存一个实体至数据库
	 * 
	 * @param entity
	 */
	public void save(E entity);

	/**
	 * 更新一个实体至数据库
	 * 
	 * @param entity
	 */
	public void update(E entity);

	/**
	 * 根据条件更新数据
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public int update(Map<String, Object> params, IFilter filter) throws Exception;

	/**
	 * 更新数据，忽略空值
	 * 
	 * @param entity
	 */
	public void updateIgnoreEmpty(E entity);

	/**
	 * 保存一个实体，存在则更新
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(E entity);

	/**
	 * 删除
	 * 
	 * @param entity
	 */
	public void delete(E entity);

	/**
	 * 根据主键删除
	 * 
	 * @param pk
	 */
	public void delByPrimaryKey(PK pk);

	/**
	 * 根据条件删除
	 * 
	 * @param filter
	 * @throws Exception
	 * @return the deleted row count
	 */
	public int delete(IFilter filter) throws Exception;

	/**
	 * 删除,关联
	 * 
	 * @param filter
	 * @throws Exception
	 * @return the deleted row count
	 */
	public int deleteWithCascadeFilter(IFilter filter) throws Exception;

	/**
	 * 查询
	 * 
	 * @param sort
	 * @return
	 */
	public List<E> all(Sort sort);

	/**
	 * 查询,不支持分页
	 * 
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<E> search(IFilter filter) throws Exception;

	/**
	 * 查询,支持排序
	 * 
	 * @param filter
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<E> search(IFilter filter, Sort sort) throws Exception;

	/**
	 * 查询,支持分页和排序
	 * 
	 * @param from
	 * @param scope
	 * @param sort
	 *            {@link Sort}
	 * @return
	 */
	public IPagination<E> search(int from, int scope, Sort sort);

	/**
	 * 查询,支持分页和排序
	 * 
	 * @param from
	 * @param scope
	 * @param sort
	 *            {@link Sort}
	 * @return
	 * @throws Exception
	 */
	public IPagination<E> search(IFilter filter, int from, int scope, Sort sort) throws Exception;

	/**
	 * 查询唯一的信息
	 * 
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public E unique(IFilter filter) throws Exception;

	/**
	 * 统计
	 * 
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public long count(IFilter filter) throws Exception;

	/**
	 * 统计
	 * 
	 * @return
	 */
	public long count();

	/**
	 * 推送
	 */
	public void flush();

}
