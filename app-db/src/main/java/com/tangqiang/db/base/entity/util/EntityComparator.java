package com.tangqiang.db.base.entity.util;

import java.util.Comparator;
import java.util.Iterator;

import com.tangqiang.db.base.entity.IEntity;


/**
 * 实体按排序{@link Sort}比较
 *
 * @author 汤强
 * @since 2013-7-30 下午5:36:12
 * @project app-db
 * @package com.tangqiang.db.base.entity.util
 */
public class EntityComparator implements Comparator<IEntity> {

	private Sort sort;

	/**
	 * Creates a new <code>EntityComparator</code> instance.
	 * 
	 * @param orderBy
	 *            an order <code>String</code> value
	 */
	public EntityComparator(Sort sort) {
		this.sort = sort;
	}

	/**
	 * 比较
	 */
	public int compare(IEntity entity1, IEntity entity2) {
		Iterator<String> iter = this.sort.getSorters().keySet().iterator();
		while (iter.hasNext()) {

			String fieldName = (String) iter.next();
			int result = compare(entity1, entity2, fieldName, this.sort
					.getSorters().get(fieldName));
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}

	/**
	 * Compares the order fields of two entities and return the result.
	 * 
	 * @param o1
	 *            a <code>EntityInterface</code> value
	 * @param o2
	 *            a <code>EntityInterface</code> value
	 * @param fieldName
	 *            a <code>String</code> value
	 * @param sortType
	 *            a <code>int</code> value
	 * @return a <code>int</code> value
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int compare(IEntity o1, IEntity o2,
			String fieldName, int sortType) {
		int rt = 0;
		try {
			if (o1 == null && o2 == null) {
				return 0;
			}
			if (o1 == null && o2 != null) {
				return -1 * sortType;
			}
			if (o1 != null && o2 == null) {
				return sortType;
			}
			if (o1.get(fieldName) == null && o2.get(fieldName) == null) {
				return 0;
			}
			if (o1.get(fieldName) == null && o2.get(fieldName) != null) {
				return -1 * sortType;
			}
			if (o1.get(fieldName) != null && o2.get(fieldName) == null) {
				return sortType;
			}
			rt = sortType
					* ((Comparable) o1.get(fieldName)).compareTo(o2
							.get(fieldName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
	}
}
