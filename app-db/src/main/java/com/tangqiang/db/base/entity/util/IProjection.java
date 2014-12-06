package com.tangqiang.db.base.entity.util;

import java.util.Collection;
import java.util.List;

import com.tangqiang.db.base.entity.IEntity;
import com.tangqiang.db.base.entity.IFilter;

/**
 * 实体资源与过滤器(db,list,file,eg)  {@link IFilter}
 * 
 * @author 汤强
 * @since 2013-7-30 下午5:15:12
 * @project app-db
 * @package com.tangqiang.db.base.entity
 */
public interface IProjection {
	/**
	 * 检测实体是否匹配过滤器
	 * 
	 * @param entity
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public boolean fit(IEntity entity, IFilter filter) throws Exception;

	/**
	 * get the projection of entities under the filter
	 * 
	 * @param entities
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<IEntity> filter(Collection<IEntity> entities, IFilter filter) throws Exception;

	/**
	 * get the projection of entities from entity source,such as database,xml file, under the filter
	 * 
	 * @param filter
	 * @return
	 */
	public List<IEntity> filter(IFilter filter);

}
