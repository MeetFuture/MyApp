package com.tangqiang.db.base.entity.enumaration;

/**
 * 关联语句
 *
 * @author 汤强
 * @since 2013-7-30 下午5:44:31
 * @project app-db
 * @package com.tangqiang.db.base.entity.enumaration
 */
public enum Relation {
	AND("and"), OR("or");
	private String value;

	public String getValue() {
		return value;
	}

	private Relation(String value) {
		this.value = value;
	}
}
