package com.tangqiang.db.base.orm.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tangqiang.db.base.orm.IDao;
import com.tangqiang.db.base.pagination.IPagination;

/**
 * hibernate数据操作接口
 * 
 * @author 汤强
 * @since 2013-7-30 下午7:48:52
 * @project app-db
 * @package com.tangqiang.db.base.orm.hibernate
 * @param <E>
 *            实体
 * @param <PK>
 *            唯一序列号
 */
public interface IHibernateDao<E, PK extends Serializable> extends IDao<E, PK> {
	/**
	 * 获取session工厂
	 * 
	 * @return
	 */
	public SessionFactory getSessionFactory();

	/**
	 * 设置session工厂
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory);

	/**
	 * 得到session
	 * 
	 * @return
	 */
	public Session getSession();

	/**
	 * 打开session
	 * 
	 * @return
	 */
	public Session openSession();

	/**
	 * 根据hql查询数据
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<E> findByHql(String hql, Object... params);

	/**
	 * 根据hql查询数据
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<E> findByHql(String hql, Map<String, Object> params);

	/**
	 * 根据hql查询数据
	 * 
	 * @param hql
	 * @param from
	 * @param scope
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public IPagination<E> findByHql(String hql, int from, int scope, Object... params) throws Exception;

	/**
	 * 根据hql查询数据
	 * 
	 * @param hql
	 * @param from
	 * @param scope
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public IPagination<E> findByHql(String hql, int from, int scope, Map<String, Object> params) throws Exception;

	/**
	 * 根据sql查询数据
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<E> findBySQL(String sql, Object... params);

	/**
	 * 根据sql查询数据
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<E> findBySQL(String sql, Map<String, Object> params);

	/**
	 * 根据sql查询数据
	 * 
	 * @param sql
	 * @param from
	 * @param scope
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public IPagination<E> findBySQL(String sql, int from, int scope, Object... params) throws Exception;

	/**
	 * 根据sql查询数据
	 * 
	 * @param sql
	 * @param from
	 * @param scope
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public IPagination<E> findBySQL(String sql, int from, int scope, Map<String, Object> params) throws Exception;

	/**
	 * 根据sql查询数据条数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public long countBySQL(String sql, Object... params) throws Exception;

	/**
	 * 根据hql查询数据条数
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public long countByHql(String hql, Object... params) throws Exception;

	/**
	 * 根据sql查询数据条数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public long countBySQL(String sql, Map<String, Object> params) throws Exception;

	/**
	 * 根据hql查询数据条数
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public long countByHql(String hql, Map<String, Object> params) throws Exception;
}
